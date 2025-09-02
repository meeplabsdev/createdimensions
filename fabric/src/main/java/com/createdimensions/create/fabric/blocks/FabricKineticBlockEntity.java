package com.createdimensions.create.fabric.blocks;

import com.createdimensions.content.registry.ModBlockEntities;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;

public class FabricKineticBlockEntity extends KineticBlockEntity {
	public FabricKineticBlockEntity(BlockPos pos, BlockState state) {
		super(ModBlockEntities.TEST_KINETIC_BLOCK_ENTITY.get(), pos, state);
	}

	@Override
	public float calculateStressApplied() {
		return (float) Math.floor(Math.abs(this.getSpeed()) / 2);
	}

	@Override
	protected void write(NbtCompound compound, boolean clientPacket) {
		super.write(compound, clientPacket);
	}

	@Override
	protected void read(NbtCompound compound, boolean clientPacket) {
		super.read(compound, clientPacket);
	}
}
