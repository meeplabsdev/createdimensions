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

import java.util.Objects;
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
		if (!Objects.equals(world.getDimensionKey().getValue().toString(), "createdimensions:pocket_dimension")) {
			Vec3d pos = user.getPos();
			NbtCompound position = new NbtCompound();
			position.putDouble("x", pos.x);
			position.putDouble("y", pos.y);
			position.putDouble("z", pos.z);

			NbtCompound origin = new NbtCompound();
			origin.putString("id", world.getRegistryKey().getValue().toString());

			user.getStackInHand(hand).setSubNbt("position", position);
			user.getStackInHand(hand).setSubNbt("origin", origin);

			ModDimensions.sendTo(server, user, RegistryKey.of(RegistryKeys.WORLD,
				Identifier.of(CreateDimensionsMod.MOD_ID, user.getUuid().toString())));
		} else {
			NbtCompound position = user.getStackInHand(hand).getSubNbt("position");
			assert position != null;

			NbtCompound origin = user.getStackInHand(hand).getSubNbt("origin");
			assert origin != null;

			double x = position.getDouble("x");
			double y = position.getDouble("y");
			double z = position.getDouble("z");

			String origin_world = origin.getString("id");
			server.getWorlds().forEach(w -> {
				if (w.getRegistryKey().getValue().toString().equals(origin_world)) {
					user.teleport(w, x, y, z, Set.of(), user.getYaw(), user.getPitch());
				}
			});
		}

		user.getItemCooldownManager().set(this, 60);
		return TypedActionResult.success(user.getStackInHand(hand));
	}
}
