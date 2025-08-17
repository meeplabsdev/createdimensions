package com.createdimensions.content.item;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.registry.ModDimensions;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionTypes;

import java.util.Set;

public class DimensionalMirrorItem extends Item {
	public DimensionalMirrorItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));
		MinecraftServer server = world.getServer();
		assert server != null;

		user.setVelocity(0, 0, 0);
		if (world.getDimensionKey() == DimensionTypes.OVERWORLD) {
			Vec3d pos = user.getPos();
			NbtCompound position = new NbtCompound();
			position.putDouble("x", pos.x);
			position.putDouble("y", pos.y);
			position.putDouble("z", pos.z);
			user.getStackInHand(hand).setSubNbt("position", position);

			ModDimensions.sendTo(server, user, RegistryKey.of(RegistryKeys.WORLD,
				Identifier.of(CreateDimensionsMod.MOD_ID, user.getUuid().toString())));
		} else {
			NbtCompound position = user.getStackInHand(hand).getSubNbt("position");
			assert position != null;

			double x = position.getDouble("x");
			double y = position.getDouble("y");
			double z = position.getDouble("z");

			user.teleport(server.getWorld(World.OVERWORLD), x, y, z, Set.of(), user.getYaw(), user.getPitch());
		}

		user.getItemCooldownManager().set(this, 60);
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}
