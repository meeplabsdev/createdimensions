package com.createdimensions.fabric.datagen;

import com.createdimensions.content.registry.ModBlocks;
import com.createdimensions.content.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;

public class LanguageGenerator extends FabricLanguageProvider {
	protected LanguageGenerator(FabricDataOutput dataOutput) {
		super(dataOutput);
	}

	@Override
	public void generateTranslations(TranslationBuilder translationBuilder) {
		// Blocks
		translationBuilder.add(ModBlocks.DIMENSIONAL_CASING.get(), "Noctium Casing");
		translationBuilder.add(ModBlocks.DIMENSIONAL_GLASS.get(), "Noctium Composite");
		translationBuilder.add(ModBlocks.KINETIC_BLOCK.get(), "Dimensional Gateway");

		// Items
		translationBuilder.add(ModItems.DIMENSIONAL_MIRROR.get(), "Mirror of Shadows");
		translationBuilder.add(ModItems.DIMENSIONAL_FRUIT.get(), "Forbidden Fruit");

		translationBuilder.add(ModItems.DIMENSIONAL_CRYSTAL.get(), "Noctium Crystal");
		translationBuilder.add(ModItems.DIMENSIONAL_LENS.get(), "Gilded Lens");
		translationBuilder.add(ModItems.DIMENSIONAL_ORB.get(), "Infused Orb");
		translationBuilder.add(ModItems.DIMENSIONAL_SHARD.get(), "Noctium Shard");

		translationBuilder.add(ModItems.DIMENSIONAL_SCRAP.get(), "Noctium Scrap");
		translationBuilder.add(ModItems.DIMENSIONAL_INGOT.get(), "Noctium Ingot");
		translationBuilder.add(ModItems.DIMENSIONAL_NUGGET.get(), "Noctium Chunk");
		translationBuilder.add(ModItems.DIMENSIONAL_DUST.get(), "Noctium Powder");
		translationBuilder.add(ModItems.DIMENSIONAL_ROD.get(), "Noctium Rod");
		translationBuilder.add(ModItems.DIMENSIONAL_SHEET.get(), "Noctium Sheet");

		// Tabs
		translationBuilder.add("category.createdimensions", "Create: Dimensions");

		// Advancements
		translationBuilder.add("advancement.createdimensions.root", "Create: Dimensions");
		translationBuilder.add("advancement.createdimensions.root.description",
			"Adventure awaits...");

		translationBuilder.add("advancement.createdimensions.dimensional_casing", "Dimensional Entrepreneur");
		translationBuilder.add("advancement.createdimensions.dimensional_casing.description",
			"Obtain your first dimensional casing");

		translationBuilder.add("advancement.createdimensions.fluid_alchemist", "Fluid Alchemist");
		translationBuilder.add("advancement.createdimensions.fluid_alchemist.description",
			"Obtain your first bucket of dimensional fluid");

		translationBuilder.add("advancement.createdimensions.gateway_architect", "Gateway Architect");
		translationBuilder.add("advancement.createdimensions.gateway_architect.description",
			"Construct the complete dimensional gateway");

		translationBuilder.add("advancement.createdimensions.master_of_dimensions", "Master of Dimensions");
		translationBuilder.add("advancement.createdimensions.master_of_dimensions.description",
			"Create three distinct pocket dimensions");

		translationBuilder.add("advancement.createdimensions.key_holder", "Key Holder");
		translationBuilder.add("advancement.createdimensions.key_holder.description",
			"Craft and bind your first dimensional key");

		translationBuilder.add("advancement.createdimensions.lost_and_found", "Lost & Found");
		translationBuilder.add("advancement.createdimensions.lost_and_found.description",
			"Create a copy of a dimensional key");

		translationBuilder.add("advancement.createdimensions.first_steps", "First Steps");
		translationBuilder.add("advancement.createdimensions.first_steps.description",
			"Enter your pocket dimension for the first time");

		translationBuilder.add("advancement.createdimensions.expulsion_protocol", "Expulsion Protocol");
		translationBuilder.add("advancement.createdimensions.expulsion_protocol.description",
			"Experience forced ejection from your pocket dimension");

		translationBuilder.add("advancement.createdimensions.forced_shutdown", "Forced Shutdown");
		translationBuilder.add("advancement.createdimensions.forced_shutdown.description",
			"Destroy a dimensional gateway with a player still inside");
	}
}
