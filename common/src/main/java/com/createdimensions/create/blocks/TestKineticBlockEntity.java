package com.createdimensions.create.blocks;


import com.createdimensions.create.CreateService;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class TestKineticBlockEntity extends BlockWithEntity {
	public TestKineticBlockEntity(Settings settings) {
		super(settings);
	}

	@Override
	public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		if (CreateService.isCreateLoaded()) {
			return CreateService.createKineticBlockEntity(pos, state);
		}

		return null;
	}
}
