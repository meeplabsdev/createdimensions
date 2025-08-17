package com.createdimensions.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class CreateDimensionsModFabricDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(ModelGenerator::new);
		pack.addProvider(RecipeGenerator::new);

		// to fix a bug where the data generator hangs when done
		// very hacky
		pack.addProvider(ExitDataProvider::new);
	}
}
