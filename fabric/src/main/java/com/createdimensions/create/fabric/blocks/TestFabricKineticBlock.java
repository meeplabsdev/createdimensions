package com.createdimensions.create.fabric.blocks;

import com.createdimensions.content.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

public class TestFabricKineticBlock extends KineticBlock implements IBE<TestFabricKineticBlockEntity> {
	public TestFabricKineticBlock(Settings properties) {
		super(properties);
	}

	@Override
	public Direction.Axis getRotationAxis(BlockState blockState) {
		return Direction.Axis.Y;
	}

	@Override
	public boolean hasShaftTowards(WorldView world, BlockPos pos, BlockState state, Direction face) {
		return face.getAxis() == getRotationAxis(state);
	}

	@Override
	public Class<TestFabricKineticBlockEntity> getBlockEntityClass() {
		return TestFabricKineticBlockEntity.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public BlockEntityType<? extends TestFabricKineticBlockEntity> getBlockEntityType() {
		return (BlockEntityType<? extends TestFabricKineticBlockEntity>) ModBlockEntities.TEST_KINETIC_BLOCK_ENTITY.get();
	}

	@Override
	public SpeedLevel getMinimumRequiredSpeedLevel() {
		return SpeedLevel.FAST;
	}
}
