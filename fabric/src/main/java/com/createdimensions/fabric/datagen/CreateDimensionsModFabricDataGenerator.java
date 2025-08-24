package com.createdimensions.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class CreateDimensionsModFabricDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(RecipeGenerator::new);
		pack.addProvider(AdvancementGenerator::new);
		pack.addProvider(WorldGenGenerator::new);
		pack.addProvider(LanguageGenerator::new);

		// to fix a bug where the data
		// generator hangs when done, very hacky
		pack.addProvider(ExitDataProvider::new);
	}

	@Override
	public void buildRegistry(RegistryBuilder registryBuilder) {
		registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, WorldGenGenerator.OreGeneration::bootstrapConfiguredOres);
		registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, WorldGenGenerator.OreGeneration::bootstrapPlacedOres);
	}
}
