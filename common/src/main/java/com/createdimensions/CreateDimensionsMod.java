package com.createdimensions;

import com.createdimensions.content.registry.*;
import com.google.common.base.Suppliers;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

public final class CreateDimensionsMod {
	public static final String MOD_ID = "createdimensions";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static final Supplier<RegistrarManager> MANAGER = Suppliers.memoize(() -> RegistrarManager.get(MOD_ID));

	public static void onInitialize() {
		ModEffects.register();
		ModBlocks.register();
		ModBlockEntities.register();
		ModItems.register();
		ModTabs.register();

		LOGGER.info("v{} loaded successfully!", Platform.getMod(MOD_ID).getVersion());
	}

	public static <T> Registrar<T> getRegistrar(RegistryKey<Registry<T>> key) {
		return MANAGER.get().get(key);
	}
}
