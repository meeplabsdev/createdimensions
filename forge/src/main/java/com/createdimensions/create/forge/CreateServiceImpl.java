package com.createdimensions.create.forge;

import com.createdimensions.create.forge.blocks.TestForgeKineticBlock;
import com.createdimensions.create.forge.blocks.TestForgeKineticBlockEntity;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.ModList;

public class CreateServiceImpl {
	public static boolean isCreateLoaded() {
		return ModList.get().isLoaded("create");
	}

	public static Class<? extends Block> getKineticBlockClass() {
		return TestForgeKineticBlock.class;
	}

	public static BlockEntity createKineticBlockEntity(BlockPos pos, BlockState state) {
		return new TestForgeKineticBlockEntity(pos, state);
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
