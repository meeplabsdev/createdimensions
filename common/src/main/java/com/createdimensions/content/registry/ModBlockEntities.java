package com.createdimensions.content.registry;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.create.CreateService;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
	public static RegistrySupplier<BlockEntityType<?>> TEST_KINETIC_BLOCK_ENTITY;

	public static void register() {
		Registrar<BlockEntityType<?>> BLOCK_ENTITIES = CreateDimensionsMod.getRegistrar(RegistryKeys.BLOCK_ENTITY_TYPE);

		TEST_KINETIC_BLOCK_ENTITY = BLOCK_ENTITIES.register(new Identifier(CreateDimensionsMod.MOD_ID, "kinetic_block_entity"),
			() -> BlockEntityType.Builder.create(CreateService::createKineticBlockEntity, ModBlocks.TEST_KINETIC_BLOCK.get()).build(null));
	}
}