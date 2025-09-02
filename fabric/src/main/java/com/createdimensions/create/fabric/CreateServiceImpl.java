package com.createdimensions.create.fabric;

import com.createdimensions.create.fabric.blocks.FabricKineticBlock;
import com.createdimensions.create.fabric.blocks.FabricKineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CreateServiceImpl {
	public static boolean isCreateLoaded() {
		return FabricLoader.getInstance().isModLoaded("create");
	}

	public static Class<? extends Block> getKineticBlockClass() {
		return FabricKineticBlock.class;
	}

	public static BlockEntity createKineticBlockEntity(BlockPos pos, BlockState state) {
		return new FabricKineticBlockEntity(pos, state);
	}

	public static float getRotationSpeed(BlockEntity blockEntity) {
		if (blockEntity instanceof KineticBlockEntity kinetic) {
			return kinetic.getSpeed();
		}
		return 0;
	}

	public static boolean hasRotationalPower(World world, BlockPos pos, float requiredSpeed) {
		BlockEntity be = world.getBlockEntity(pos);
		if (be instanceof KineticBlockEntity kinetic) {
			return Math.abs(kinetic.getSpeed()) >= requiredSpeed;
		}
		return false;
	}
}

