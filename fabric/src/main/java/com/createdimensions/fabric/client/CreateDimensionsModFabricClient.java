package com.createdimensions.fabric.client;

import com.createdimensions.client.CreateDimensionsModClient;
import com.createdimensions.content.registry.ModBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public final class CreateDimensionsModFabricClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		CreateDimensionsModClient.onInitializeClient();

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DIMENSIONAL_GLASS.get(), RenderLayer.getTranslucent());
	}
}
