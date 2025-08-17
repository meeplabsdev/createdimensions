package com.createdimensions.fabric.datagen;

import com.createdimensions.content.registry.ModBlocks;
import com.createdimensions.content.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

public class ModelGenerator extends FabricModelProvider {
	public ModelGenerator(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
		for (Block block : ModBlocks.blocks) blockStateModelGenerator.registerSimpleCubeAll(block);
	}

	@Override
	public void generateItemModels(ItemModelGenerator itemModelGenerator) {
		for (Item item : ModItems.items) itemModelGenerator.register(item, Models.GENERATED);
	}
}
