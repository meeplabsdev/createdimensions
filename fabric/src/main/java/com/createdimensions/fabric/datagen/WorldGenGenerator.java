package com.createdimensions.fabric.datagen;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.registry.ModBlocks;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.*;
import net.minecraft.structure.rule.BlockMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class WorldGenGenerator extends FabricDynamicRegistryProvider {
	public WorldGenGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(RegistryWrapper.WrapperLookup wrapperLookup, Entries entries) {
		OreGeneration.registerOres();

		entries.addAll(wrapperLookup.getWrapperOrThrow(RegistryKeys.CONFIGURED_FEATURE));
		entries.addAll(wrapperLookup.getWrapperOrThrow(RegistryKeys.PLACED_FEATURE));
	}

	@Override
	public String getName() {
		return "World Gen";
	}

	static class OreGeneration {
		public static final RegistryKey<PlacedFeature> DIMENSIONAL_ORE_PLACED = RegistryKey.of(RegistryKeys.PLACED_FEATURE,
			new Identifier(CreateDimensionsMod.MOD_ID, "dimensional_ore"));
		public static final RegistryKey<ConfiguredFeature<?, ?>> DIMENSIONAL_ORE_CONFIGURED = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE,
			new Identifier(CreateDimensionsMod.MOD_ID, "dimensional_ore"));

		public static void registerOres() {
			BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.UNDERGROUND_DECORATION, DIMENSIONAL_ORE_PLACED);
		}

		public static void bootstrapConfiguredOres(Registerable<ConfiguredFeature<?, ?>> context) {
			List<OreFeatureConfig.Target> endOres = List.of(
				OreFeatureConfig.createTarget(new BlockMatchRuleTest(Blocks.END_STONE), ModBlocks.DIMENSIONAL_CASING.get().getDefaultState())
			);

			context.register(DIMENSIONAL_ORE_CONFIGURED, new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(endOres, 12)));
		}

		public static void bootstrapPlacedOres(Registerable<PlacedFeature> context) {
			final RegistryEntryLookup<ConfiguredFeature<?, ?>> configuredFeatures = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);

			context.register(DIMENSIONAL_ORE_PLACED, new PlacedFeature(configuredFeatures.getOrThrow(DIMENSIONAL_ORE_CONFIGURED),
				List.of(
					CountPlacementModifier.of(12), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(
					YOffset.fixed(-80), YOffset.fixed(80)), BiomePlacementModifier.of()
				)
			));
		}
	}
}
