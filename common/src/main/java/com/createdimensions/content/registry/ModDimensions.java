package com.createdimensions.content.registry;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.dimension.PocketDimensionWorldProperties;
import com.createdimensions.mixin.accessors.MinecraftServerAccessor;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.datafixer.Schemas;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListenerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.RandomSequencesState;
import net.minecraft.util.path.AllowedSymlinkPathMatcher;
import net.minecraft.util.path.SymlinkFinder;
import net.minecraft.util.path.SymlinkValidationException;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.chunk.FlatChunkGenerator;
import net.minecraft.world.gen.chunk.FlatChunkGeneratorConfig;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.level.storage.LevelStorage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class ModDimensions {
	static final LevelStorage LEVEL_STORAGE = new LevelStorage(Path.of("createdimension_saves"),
		Path.of("createdimension_backups"),
		new SymlinkFinder(new AllowedSymlinkPathMatcher(ImmutableList.of())),
		Schemas.getFixer());

	public static final RegistryKey<DimensionType> POCKET_DIMENSION = RegistryKey.of(RegistryKeys.DIMENSION_TYPE,
		Identifier.of(CreateDimensionsMod.MOD_ID, "pocket_dimension"));

	public static ServerWorld createWorld(MinecraftServer server, RegistryKey<World> worldKey) throws IOException, SymlinkValidationException {
		ExecutorService executor = Util.getMainWorkerExecutor();
		LevelStorage.Session storageSession = LEVEL_STORAGE.createSession("pocket");
		WorldGenerationProgressListenerFactory progressListenerFactory = ((MinecraftServerAccessor) server).getWorldGenerationProgressListenerFactory();
		ServerWorldProperties worldProperties = new PocketDimensionWorldProperties();

		DynamicRegistryManager registryManager = server.getOverworld().getRegistryManager();
		Registry<DimensionType> dimensionTypeRegistry = registryManager.get(RegistryKeys.DIMENSION_TYPE);
		Registry<Biome> biomeRegistry = registryManager.get(RegistryKeys.BIOME);

		DimensionType dimensionType = dimensionTypeRegistry.get(ModDimensions.POCKET_DIMENSION);
		Biome biome = biomeRegistry.get(BiomeKeys.THE_VOID);

		DimensionOptions dimensionOptions = new DimensionOptions(
			dimensionTypeRegistry.getEntry(dimensionType),
			new FlatChunkGenerator(new FlatChunkGeneratorConfig(Optional.of(RegistryEntryList.of()),
				biomeRegistry.getEntry(biome),
				ImmutableList.of())));

		ServerWorld serverWorld = new ServerWorld(
			server,
			executor,
			storageSession,
			worldProperties,
			worldKey,
			dimensionOptions,
			progressListenerFactory.create(11),
			false,
			0,
			ImmutableList.of(),
			false,
			new RandomSequencesState(0));

		((MinecraftServerAccessor) server).getWorlds().put(worldKey, serverWorld);
		serverWorld.setBlockState(new BlockPos(0, 63, 0), Blocks.BEDROCK.getDefaultState());
		serverWorld.tick(() -> true);

		return serverWorld;
	}

	public static void sendTo(MinecraftServer server, PlayerEntity user, RegistryKey<World> worldKey) {
		if (server.getWorldRegistryKeys().contains(worldKey)) {
			ServerWorld serverWorld = Objects.requireNonNull(server.getWorld(worldKey));
			serverWorld.breakBlock(new BlockPos(0, 64, 0), true);
			serverWorld.breakBlock(new BlockPos(0, 65, 0), true);
			user.teleport(serverWorld, 0.5, 64, 0.5, Set.of(), user.getYaw(), user.getPitch());
		} else {
			try {
				ServerWorld serverWorld = ModDimensions.createWorld(server, worldKey);
				user.teleport(serverWorld, 0.5, 64, 0.5, Set.of(), user.getYaw(), user.getPitch());
			} catch (IOException | SymlinkValidationException ignored) { }
		}
	}
}
