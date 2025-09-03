package com.createdimensions.content.item;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.registry.ModBlockEntities;
import com.createdimensions.content.registry.ModDimensions;
import com.createdimensions.create.CreateService;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class DimensionalMirrorItem extends Item {
	public DimensionalMirrorItem(Settings settings) {
		super(settings);
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
		if (world.isClient()) return TypedActionResult.success(user.getStackInHand(hand));
		MinecraftServer server = world.getServer();
		assert server != null;

		if (user.isSneaking()) {
			HitResult hit = user.raycast(20, 0, false);
			if (hit.getType() == HitResult.Type.BLOCK) {
				Vec3d hitPos = hit.getPos();
				BlockPos blockPos = BlockPos.ofFloored(hitPos.x, hitPos.y, hitPos.z);
				BlockEntity blockEntity = world.getBlockEntity(blockPos);

				if (blockEntity != null && blockEntity.getType().equals(ModBlockEntities.KINETIC_BLOCK_ENTITY.get())) {
					NbtCompound linked = new NbtCompound();
					linked.putDouble("x", blockPos.getX());
					linked.putDouble("y", blockPos.getY());
					linked.putDouble("z", blockPos.getZ());
					linked.putString("origin", world.getRegistryKey().getValue().toString());

					user.getStackInHand(hand).setSubNbt("linked", linked);

					user.sendMessage(Text.literal(String.format("Linked to Dimensional Gateway at %s, %s",
						blockPos.getX(), blockPos.getZ())).setStyle(Style.EMPTY.withColor(Formatting.GREEN)));

					return TypedActionResult.success(user.getStackInHand(hand));
				} else {
					user.sendMessage(Text.literal("You are not looking at a valid gateway block")
						.setStyle(Style.EMPTY.withColor(Formatting.RED)));

					return TypedActionResult.fail(user.getStackInHand(hand));
				}
			} else {
				user.sendMessage(Text.literal("You must be looking at a gateway to link")
					.setStyle(Style.EMPTY.withColor(Formatting.RED)));

				return TypedActionResult.fail(user.getStackInHand(hand));
			}
		} else {
			NbtCompound linked = user.getStackInHand(hand).getSubNbt("linked");
			if (linked != null) {
				BlockPos linkedPos = BlockPos.ofFloored(
					linked.getDouble("x"),
					linked.getDouble("y"),
					linked.getDouble("z")
				);
				String linked_world = linked.getString("origin");
				AtomicReference<Boolean> hasPower = new AtomicReference<>(false);

				server.getWorlds().forEach(w -> {
					if (w.getRegistryKey().getValue().toString().equals(linked_world)) {
						BlockEntity blockEntity = w.getBlockEntity(linkedPos);
						if (blockEntity != null && blockEntity.getType()
							.equals(ModBlockEntities.KINETIC_BLOCK_ENTITY.get())) {
							if (CreateService.hasRotationalPower(w, linkedPos, 100)) {
								hasPower.set(true);
							}
						}
					}
				});

				if (hasPower.get()) {
					user.setVelocity(0, 0, 0);
					if (!Objects.equals(world.getDimensionKey().getValue().toString(),
						"createdimensions:pocket_dimension")) {
						Vec3d pos = user.getPos();
						NbtCompound position = new NbtCompound();
						position.putDouble("x", pos.x);
						position.putDouble("y", pos.y);
						position.putDouble("z", pos.z);
						position.putString("origin", world.getRegistryKey().getValue().toString());

						user.getStackInHand(hand).setSubNbt("position", position);

						ModDimensions.sendTo(server, user, RegistryKey.of(RegistryKeys.WORLD,
							Identifier.of(CreateDimensionsMod.MOD_ID, user.getUuid().toString())));
					} else {
						NbtCompound position = user.getStackInHand(hand).getSubNbt("position");
						assert position != null;

						double x = position.getDouble("x");
						double y = position.getDouble("y");
						double z = position.getDouble("z");
						String origin_world = position.getString("origin");

						server.getWorlds().forEach(w -> {
							if (w.getRegistryKey().getValue().toString().equals(origin_world)) {
								user.teleport(w, x, y, z, Set.of(), user.getYaw(), user.getPitch());
							}
						});
					}

					user.getItemCooldownManager().set(this, 60);
					return TypedActionResult.success(user.getStackInHand(hand));
				} else {
					user.sendMessage(Text.literal("The linked gateway is not spinning at the required rotational speed")
						.setStyle(Style.EMPTY.withColor(Formatting.RED)));

					return TypedActionResult.fail(user.getStackInHand(hand));
				}
			} else {
				user.sendMessage(Text.literal("Mirror is not linked to a gateway")
					.setStyle(Style.EMPTY.withColor(Formatting.RED)));

				return TypedActionResult.fail(user.getStackInHand(hand));
			}
		}
	}
}
