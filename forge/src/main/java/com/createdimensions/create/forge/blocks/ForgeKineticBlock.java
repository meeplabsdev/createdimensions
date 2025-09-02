package com.createdimensions.create.forge.blocks;

import com.createdimensions.content.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.base.KineticBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldView;

public class ForgeKineticBlock extends KineticBlock implements IBE<ForgeKineticBlockEntity> {
	public ForgeKineticBlock(Settings properties) {
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
	public Class<ForgeKineticBlockEntity> getBlockEntityClass() {
		return ForgeKineticBlockEntity.class;
	}

	@Override
	@SuppressWarnings("unchecked")
	public BlockEntityType<? extends ForgeKineticBlockEntity> getBlockEntityType() {
		return (BlockEntityType<? extends ForgeKineticBlockEntity>) ModBlockEntities.TEST_KINETIC_BLOCK_ENTITY.get();
	}

	@Override
	public SpeedLevel getMinimumRequiredSpeedLevel() {
		return SpeedLevel.FAST;
	}
}
