package com.createdimensions.content.registry;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.create.CreateService;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ModBlocks {
	public static List<Block> blocks = new ArrayList<>();

	public static RegistrySupplier<Block>
		DIMENSIONAL_CASING,
		DIMENSIONAL_GLASS,
		TEST_KINETIC_BLOCK;

	public static void register() {
		Register REG = new Register(
			CreateDimensionsMod.getRegistrar(RegistryKeys.BLOCK),
			CreateDimensionsMod.getRegistrar(RegistryKeys.ITEM)
		);

		DIMENSIONAL_CASING = REG.register("dimensional_casing");
		DIMENSIONAL_GLASS = REG.register("dimensional_glass", Block.Settings.copy(Blocks.GLASS).nonOpaque());
		TEST_KINETIC_BLOCK = REG.register("test_kinetic_block", CreateService.getKineticBlockClass(), Block.Settings.create());
	}

	static class Register {
		Registrar<Block> BLOCKS;
		Registrar<Item> ITEMS;

		public Register(Registrar<Block> blocks, Registrar<Item> items) {
			this.BLOCKS = blocks;
			this.ITEMS = items;
		}

		private RegistrySupplier<Block> register(String id) {
			return register(id, Block.Settings.create());
		}

		private RegistrySupplier<Block> register(String id, Block.Settings settings) {
			return register(id, Block.class, settings);
		}

		@SuppressWarnings("UnstableApiUsage")
		private <T extends Block> RegistrySupplier<Block> register(String id, Class<T> block, Block.Settings settings) {
			RegistrySupplier<Block> blockRegistrySupplier = BLOCKS.register(new Identifier(CreateDimensionsMod.MOD_ID,
					id),
				() -> _createBlock(block, settings));

			ITEMS.register(new Identifier(CreateDimensionsMod.MOD_ID, id), () -> new BlockItem(
				blockRegistrySupplier.get(),
				new Item.Settings().arch$tab(ModTabs.DIMENSIONS_TAB)
			));

			if (Platform.isFabric()) blocks.add(blockRegistrySupplier.get());
			return blockRegistrySupplier;
		}

		@SuppressWarnings("unchecked")
		public <T extends Block> Block _createBlock(Class<T> clazz, Block.Settings settings) {
			try {
				for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
					if (constructor.getParameterCount() == 1) {
						constructor.setAccessible(true);
						return (T) constructor.newInstance(settings);
					}
				}

				throw new NoSuchMethodException("No matching constructor found");
			} catch (Exception e) {
				return new Block(settings);
			}
		}
	}
}