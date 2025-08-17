package com.createdimensions.mixin.accessors;

import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldGenerationProgressListenerFactory;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(MinecraftServer.class)
public interface MinecraftServerAccessor {
	@Accessor
	WorldGenerationProgressListenerFactory getWorldGenerationProgressListenerFactory();

	@Accessor
	Map<RegistryKey<World>, ServerWorld> getWorlds();
}
