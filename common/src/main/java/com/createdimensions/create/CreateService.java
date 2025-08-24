package com.createdimensions.create;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CreateService {
	@ExpectPlatform
	public static boolean isCreateLoaded() {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static Class<? extends Block> getKineticBlockClass() {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static BlockEntity createKineticBlockEntity(BlockPos pos, BlockState state) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static float getRotationSpeed(BlockEntity blockEntity) {
		throw new AssertionError();
	}

	@ExpectPlatform
	public static boolean hasRotationalPower(World world, BlockPos pos, float requiredSpeed) {
		throw new AssertionError();
	}
}
