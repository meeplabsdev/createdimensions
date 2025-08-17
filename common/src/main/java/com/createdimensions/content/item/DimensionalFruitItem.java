package com.createdimensions.content.item;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.registry.ModDimensions;
import com.createdimensions.content.registry.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.Set;

public class DimensionalFruitItem extends Item {
	public DimensionalFruitItem(Settings settings) {
		super(settings);
	}

	@Override
	public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
		ItemStack itemStack = super.finishUsing(stack, world, user);
		if (world.isClient()) return itemStack;
		MinecraftServer server = world.getServer();
		assert server != null;

		user.setVelocity(0, 0, 0);
		if (world.getDimensionKey() == DimensionTypes.OVERWORLD) {
			ModDimensions.sendTo(server, (PlayerEntity) user, RegistryKey.of(RegistryKeys.WORLD,
				Identifier.of(CreateDimensionsMod.MOD_ID, user.getUuid().toString())));
		} else {
			Random random = world.getRandom();
			int x = random.nextBetween(-8192, 8192);
			int z = random.nextBetween(-8192, 8192);
			int y = world.getTopY() + 10;

			user.teleport(server.getWorld(World.OVERWORLD),
				x, y, z, Set.of(), user.getYaw(), user.getPitch());

			user.addStatusEffect(new StatusEffectInstance(
				StatusEffects.RESISTANCE,
				160,
				-1,
				false,
				false,
				false
			));
			user.addStatusEffect(new StatusEffectInstance(
				ModEffects.FAST_FALLING.get(),
				160,
				1,
				false,
				false,
				false
			));
		}

		((PlayerEntity) user).getItemCooldownManager().set(this, 90);
		return itemStack;
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 8;
	}

	@Override
	public SoundEvent getDrinkSound() {
		return SoundEvents.BLOCK_END_PORTAL_FRAME_FILL;
	}

	@Override
	public SoundEvent getEatSound() {
		return SoundEvents.BLOCK_END_PORTAL_FRAME_FILL;
	}
}
