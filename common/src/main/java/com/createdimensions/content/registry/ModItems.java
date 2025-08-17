package com.createdimensions.content.registry;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.item.DimensionalFruitItem;
import com.createdimensions.content.item.DimensionalMirrorItem;
import dev.architectury.platform.Platform;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ModItems {
	public static List<Item> items = new ArrayList<>();

	public static RegistrySupplier<Item>
		DIMENSIONAL_MIRROR,
		DIMENSIONAL_FRUIT,
		DIMENSIONAL_CRYSTAL,
		DIMENSIONAL_DUST,
		DIMENSIONAL_LENS,
		DIMENSIONAL_ORB,
		DIMENSIONAL_SHARD,
		DIMENSIONAL_SCRAP,
		DIMENSIONAL_INGOT,
		DIMENSIONAL_NUGGET,
		DIMENSIONAL_ROD,
		DIMENSIONAL_SHEET;

	@SuppressWarnings("UnstableApiUsage")
	public static void register() {
		final Register REG = new Register(CreateDimensionsMod.getRegistrar(RegistryKeys.ITEM));

		DIMENSIONAL_MIRROR =
			REG.register("dimensional_mirror",
				DimensionalMirrorItem.class, new Item.Settings()
					.maxCount(1)
					.rarity(Rarity.EPIC)
					.fireproof()
					.arch$tab(ModTabs.DIMENSIONS_TAB)
			);

		DIMENSIONAL_FRUIT =
			REG.register("dimensional_fruit",
				DimensionalFruitItem.class, new Item.Settings()
					.maxCount(16)
					.rarity(Rarity.RARE)
					.arch$tab(ModTabs.DIMENSIONS_TAB)
					.food(new FoodComponent.Builder()
						.alwaysEdible()
						.hunger(3)
						.statusEffect(new StatusEffectInstance(
							StatusEffects.WITHER,
							160,
							1),
						1F)
					.build()
				));

		DIMENSIONAL_CRYSTAL = REG.register("dimensional_crystal");
		DIMENSIONAL_DUST = REG.register("dimensional_dust");
		DIMENSIONAL_LENS = REG.register("dimensional_lens");
		DIMENSIONAL_ORB = REG.register("dimensional_orb");
		DIMENSIONAL_SHARD = REG.register("dimensional_shard");

		DIMENSIONAL_SCRAP = REG.register("dimensional_scrap");
		DIMENSIONAL_INGOT = REG.register("dimensional_ingot");
		DIMENSIONAL_NUGGET = REG.register("dimensional_nugget");
		DIMENSIONAL_ROD = REG.register("dimensional_rod");
		DIMENSIONAL_SHEET = REG.register("dimensional_sheet");
	}

	static class Register {
		Registrar<Item> ITEMS;

		public Register(Registrar<Item> items) {
			this.ITEMS = items;
		}

		private RegistrySupplier<Item> register(String id) {
			return register(id, new Item.Settings());
		}

		@SuppressWarnings("UnstableApiUsage")
		private RegistrySupplier<Item> register(String id, Item.Settings settings) {
			return register(id, Item.class, settings.arch$tab(ModTabs.DIMENSIONS_TAB));
		}

		private <T extends Item> RegistrySupplier<Item> register(String id, Class<T> item, Item.Settings settings) {
			RegistrySupplier<Item> itemRegistrySupplier = ITEMS.register(new Identifier(CreateDimensionsMod.MOD_ID, id),
				() -> _createItem(item, settings));

			if (Platform.isFabric()) items.add(itemRegistrySupplier.get());
			return itemRegistrySupplier;
		}

		@SuppressWarnings("unchecked")
		public <T extends Item> Item _createItem(Class<T> clazz, Item.Settings settings) {
			try {
				Constructor<?>[] constructors = clazz.getDeclaredConstructors();

				for (Constructor<?> constructor : constructors) {
					if (constructor.getParameterCount() == 1) {
						constructor.setAccessible(true);
						return (T) constructor.newInstance(settings);
					}
				}
				throw new NoSuchMethodException("No matching constructor found");
			} catch (Exception e) {
				return new Item(settings);
			}
		}
	}
}