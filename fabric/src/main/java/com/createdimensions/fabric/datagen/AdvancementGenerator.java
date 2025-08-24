package com.createdimensions.fabric.datagen;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.registry.ModBlocks;
import com.createdimensions.content.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.AdvancementRewards;
import net.minecraft.advancement.criterion.CriterionConditions;
import net.minecraft.advancement.criterion.ImpossibleCriterion;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.advancement.criterion.TickCriterion;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class AdvancementGenerator extends FabricAdvancementProvider {
	protected AdvancementGenerator(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateAdvancement(Consumer<Advancement> consumer) {
		AdvancementBuilder ROOT = new AdvancementBuilder(consumer,
			Advancement.Builder.create()
				.display(ModItems.DIMENSIONAL_INGOT.get(),
					Text.translatable("advancement.createdimensions.root"),
					Text.translatable("advancement.createdimensions.root.description"),
					Identifier.of("minecraft", "textures/gui/advancements/backgrounds/adventure.png"),
					AdvancementFrame.GOAL,
					true,
					false,
					false)
				.criterion("createdimensions", TickCriterion.Conditions.createTick())
				.build(consumer, CreateDimensionsMod.MOD_ID + "/root"));

		AdvancementBuilder DIMENSIONAL_CASING = ROOT.then(
			"dimensional_casing",
			ModBlocks.DIMENSIONAL_CASING.get().asItem(),
			InventoryChangedCriterion.Conditions.items(ModBlocks.DIMENSIONAL_CASING.get().asItem())
		);

		AdvancementBuilder GATEWAY_ARCHITECT = ROOT.then(
			"gateway_architect",
			ModBlocks.DIMENSIONAL_GLASS.get().asItem(),
			new ImpossibleCriterion.Conditions(),
			true,
			true,
			false
		);

		AdvancementBuilder MASTER_OF_DIMENSIONS = GATEWAY_ARCHITECT.then(
			"master_of_dimensions",
			ModItems.DIMENSIONAL_CRYSTAL.get(),
			new ImpossibleCriterion.Conditions()
		);

		AdvancementBuilder KEY_HOLDER = GATEWAY_ARCHITECT.then(
			"key_holder",
			Items.DIRT,
			new ImpossibleCriterion.Conditions()
		);

		AdvancementBuilder LOST_AND_FOUND = KEY_HOLDER.then(
			"lost_and_found",
			Items.DIRT,
			new ImpossibleCriterion.Conditions()
		);

		AdvancementBuilder FIRST_STEPS = KEY_HOLDER.then(
			"first_steps",
			Items.DIRT,
			new ImpossibleCriterion.Conditions()
		);

		AdvancementBuilder EXPULSION_PROTOCOL = ROOT.then(
			"expulsion_protocol",
			Items.DIRT,
			new ImpossibleCriterion.Conditions(),
			true,
			true,
			true
		);

		AdvancementBuilder FORCED_SHUTDOWN = ROOT.then(
			"forced_shutdown",
			Items.DIRT,
			new ImpossibleCriterion.Conditions(),
			true,
			true,
			true
		);
	}

	static class AdvancementBuilder {
		public Advancement root;
		Consumer<Advancement> consumer;

		public AdvancementBuilder(Consumer<Advancement> consumer, Advancement root) {
			this.consumer = consumer;
			this.root = root;
		}

		public AdvancementBuilder then(String id, ItemConvertible icon, CriterionConditions criterion) {
			return this.then(id, icon, criterion, true, false, false);
		}

		public AdvancementBuilder then(String id, ItemConvertible icon, CriterionConditions criterion, boolean showToast, boolean announceToChat, boolean hidden) {
			return this.then(id, icon, criterion, AdvancementFrame.TASK, showToast, announceToChat, hidden);
		}

		public AdvancementBuilder then(String id, ItemConvertible icon, CriterionConditions criterion, AdvancementFrame frame, boolean showToast, boolean announceToChat, boolean hidden) {
			return this.then(id, this.root, icon, criterion, frame, showToast, announceToChat, hidden);
		}

		public AdvancementBuilder then(String id, Advancement parent, ItemConvertible icon, CriterionConditions criterion, AdvancementFrame frame, boolean showToast, boolean announceToChat, boolean hidden) {
			return this.then(id,
				parent,
				icon,
				criterion,
				frame,
				AdvancementRewards.NONE,
				showToast,
				announceToChat,
				hidden);
		}

		public AdvancementBuilder then(String id, Advancement parent, ItemConvertible icon, CriterionConditions criterion, AdvancementFrame frame, AdvancementRewards rewards, boolean showToast, boolean announceToChat, boolean hidden) {
			return this.then(id,
				parent,
				"advancement.createdimensions." + id,
				"advancement.createdimensions." + id + ".description",
				icon,
				criterion,
				frame,
				rewards,
				showToast,
				announceToChat,
				hidden);
		}

		public AdvancementBuilder then(String id, Advancement parent, String title, String description, ItemConvertible icon, CriterionConditions criterion, AdvancementFrame frame, AdvancementRewards rewards, boolean showToast, boolean announceToChat, boolean hidden) {
			return this.then(id,
				parent,
				title,
				description,
				null,
				icon,
				criterion,
				frame,
				rewards,
				showToast,
				announceToChat,
				hidden);
		}

		public AdvancementBuilder then(String id, Advancement parent, String title, String description, Identifier background, ItemConvertible icon, CriterionConditions criterion, AdvancementFrame frame, AdvancementRewards rewards, boolean showToast, boolean announceToChat, boolean hidden) {
			return new AdvancementBuilder(this.consumer, Advancement.Builder.create().parent(parent).display(icon,
				title.contains(" ") ? Text.literal(title) : Text.translatable(title),
				description.contains(" ") ? Text.literal(description) : Text.translatable(description),
				background,
				frame,
				showToast,
				announceToChat,
				hidden).criterion(id, criterion).rewards(rewards).build(this.consumer,
				CreateDimensionsMod.MOD_ID + "/" + id));
		}
	}
}
