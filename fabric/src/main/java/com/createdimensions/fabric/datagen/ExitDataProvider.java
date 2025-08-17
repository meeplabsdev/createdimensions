package com.createdimensions.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;

import java.util.concurrent.CompletableFuture;

public class ExitDataProvider implements DataProvider {
	public ExitDataProvider(FabricDataOutput output) {
		super();
	}

	@Override
	public CompletableFuture<?> run(DataWriter writer) {
		System.exit(0);
		return CompletableFuture.completedFuture(null);
	}

	@Override
	public String getName() {
		return "Exits";
	}
}
