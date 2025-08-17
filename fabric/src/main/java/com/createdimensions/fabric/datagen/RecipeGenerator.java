package com.createdimensions.fabric.datagen;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.registry.ModBlocks;
import com.createdimensions.content.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;

public class RecipeGenerator extends FabricRecipeProvider {
	public RecipeGenerator(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generate(Consumer<RecipeJsonProvider> consumer) {
		RecipeProvider.offerSmelting(consumer, List.of(ModItems.DIMENSIONAL_SCRAP.get()), RecipeCategory.MISC,
			ModItems.DIMENSIONAL_INGOT.get(), 0.8F, 280, "dimensional_scrap");

		RecipeProvider.offerBlasting(consumer, List.of(ModItems.DIMENSIONAL_SCRAP.get()), RecipeCategory.MISC,
			ModItems.DIMENSIONAL_INGOT.get(), 0.8F, 120, "dimensional_scrap");

		ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIMENSIONAL_NUGGET.get(), 9).input(ModItems.DIMENSIONAL_INGOT.get())
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_NUGGET.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_NUGGET.get()))
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_INGOT.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_INGOT.get()))
			.offerTo(consumer, new Identifier(CreateDimensionsMod.MOD_ID, "dimensional_nugget_from_ingot"));

		ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIMENSIONAL_NUGGET.get()).input(ModItems.DIMENSIONAL_DUST.get(), 2)
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_NUGGET.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_NUGGET.get()))
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_DUST.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_DUST.get()))
			.offerTo(consumer, new Identifier(CreateDimensionsMod.MOD_ID, "dimensional_nugget_from_dust"));

		ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIMENSIONAL_DUST.get(), 2).input(ModItems.DIMENSIONAL_NUGGET.get())
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_DUST.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_DUST.get()))
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_NUGGET.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_NUGGET.get()))
			.offerTo(consumer);

		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIMENSIONAL_INGOT.get())
			.input('#', ModItems.DIMENSIONAL_NUGGET.get())
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_NUGGET.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_NUGGET.get()))
			.offerTo(consumer);

		ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DIMENSIONAL_GLASS.get().asItem())
			.input('d', ModItems.DIMENSIONAL_DUST.get())
			.input('g', Items.GLASS)
			.pattern("ddd")
			.pattern("dgd")
			.pattern("ddd")
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_DUST.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_DUST.get()))
			.criterion(FabricRecipeProvider.hasItem(Items.GLASS), FabricRecipeProvider.conditionsFromItem(Items.GLASS))
			.offerTo(consumer);

		ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.DIMENSIONAL_MIRROR.get())
			.input('r', ModItems.DIMENSIONAL_ROD.get())
			.input('l', ModItems.DIMENSIONAL_LENS.get())
			.input('o', ModItems.DIMENSIONAL_ORB.get())
			.pattern(" l ")
			.pattern(" ol")
			.pattern("r  ")
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_ROD.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_ROD.get()))
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_LENS.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_LENS.get()))
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_ORB.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_ORB.get()))
			.offerTo(consumer);

		ShapedRecipeJsonBuilder.create(RecipeCategory.FOOD, ModItems.DIMENSIONAL_FRUIT.get(), 3)
			.input('l', ModItems.DIMENSIONAL_LENS.get())
			.input('f', Items.CHORUS_FRUIT)
			.pattern("lf")
			.pattern("ff")
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_LENS.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_LENS.get()))
			.criterion(FabricRecipeProvider.hasItem(Items.CHORUS_FRUIT), FabricRecipeProvider.conditionsFromItem(Items.CHORUS_FRUIT))
			.offerTo(consumer);

		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIMENSIONAL_ORB.get())
			.input('d', ModItems.DIMENSIONAL_DUST.get())
			.input('n', ModItems.DIMENSIONAL_NUGGET.get())
			.input('p', Items.ENDER_PEARL)
			.pattern("dnd")
			.pattern("npn")
			.pattern("dnd")
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_DUST.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_DUST.get()))
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_NUGGET.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_NUGGET.get()))
			.criterion(FabricRecipeProvider.hasItem(Items.ENDER_PEARL), FabricRecipeProvider.conditionsFromItem(Items.ENDER_PEARL))
			.offerTo(consumer);

		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIMENSIONAL_CRYSTAL.get(), 4)
			.input('s', ModItems.DIMENSIONAL_SHARD.get())
			.input('c', Items.END_CRYSTAL)
			.pattern("sss")
			.pattern("scs")
			.pattern("sss")
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_SHARD.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_SHARD.get()))
			.criterion(FabricRecipeProvider.hasItem(Items.END_CRYSTAL), FabricRecipeProvider.conditionsFromItem(Items.END_CRYSTAL))
			.offerTo(consumer);

		ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.DIMENSIONAL_LENS.get())
			.input('g', ModBlocks.DIMENSIONAL_GLASS.get().asItem())
			.input('n', ModItems.DIMENSIONAL_NUGGET.get())
			.input('c', ModItems.DIMENSIONAL_CRYSTAL.get())
			.input('s', ModItems.DIMENSIONAL_SHARD.get())
			.pattern("gng")
			.pattern("csc")
			.pattern("gng")
			.criterion(FabricRecipeProvider.hasItem(ModBlocks.DIMENSIONAL_GLASS.get().asItem()), FabricRecipeProvider.conditionsFromItem(ModBlocks.DIMENSIONAL_GLASS.get().asItem()))
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_NUGGET.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_NUGGET.get()))
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_CRYSTAL.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_CRYSTAL.get()))
			.criterion(FabricRecipeProvider.hasItem(ModItems.DIMENSIONAL_SHARD.get()), FabricRecipeProvider.conditionsFromItem(ModItems.DIMENSIONAL_SHARD.get()))
			.offerTo(consumer);
	}
}
