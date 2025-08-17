package com.createdimensions.forge;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.registry.ModBlocks;
import com.createdimensions.forge.client.CreateDimensionsModForgeClient;
import dev.architectury.platform.Platform;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreateDimensionsMod.MOD_ID)
public final class CreateDimensionsModForge {
	@SuppressWarnings("removal")
	public CreateDimensionsModForge() {
		EventBuses.registerModEventBus(CreateDimensionsMod.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
		CreateDimensionsMod.onInitialize();
	}
}
