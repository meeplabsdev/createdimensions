package com.createdimensions.content.registry;

import com.createdimensions.CreateDimensionsMod;
import dev.architectury.registry.CreativeTabRegistry;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;

public class ModTabs {
	private static final DeferredRegister<ItemGroup> TABS = DeferredRegister.create(CreateDimensionsMod.MOD_ID, RegistryKeys.ITEM_GROUP);

	public static final RegistrySupplier<ItemGroup> DIMENSIONS_TAB = TABS.register("createdimensions",
		() -> CreativeTabRegistry.create(Text.translatable("category.createdimensions"),
			() -> new ItemStack(Items.GLOWSTONE)));

	public static void register() {
		TABS.register();
	}
}
