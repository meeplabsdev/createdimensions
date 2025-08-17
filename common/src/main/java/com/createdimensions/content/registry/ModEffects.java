package com.createdimensions.content.registry;

import com.createdimensions.CreateDimensionsMod;
import com.createdimensions.content.effect.FastFallingEffect;
import dev.architectury.registry.registries.Registrar;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class ModEffects {
	public static RegistrySupplier<StatusEffect>
		FAST_FALLING;

	public static void register() {
		Register REG = new Register(CreateDimensionsMod.getRegistrar(RegistryKeys.STATUS_EFFECT));

		FAST_FALLING = REG.register("fast_falling", FastFallingEffect.class, StatusEffectCategory.NEUTRAL, 0);
	}

	static class Register {
		Registrar<StatusEffect> EFFECTS;

		public Register(Registrar<StatusEffect> effects) {
			this.EFFECTS = effects;
		}

		private RegistrySupplier<StatusEffect> register(String id) {
			return register(id, StatusEffectCategory.NEUTRAL, 0);
		}

		private RegistrySupplier<StatusEffect> register(String id, StatusEffectCategory category, int colour) {
			return register(id, FastFallingEffect.class, category, colour);
		}

		private <T extends StatusEffect> RegistrySupplier<StatusEffect> register(String id, Class<T> effect, StatusEffectCategory category, int colour) {
			return EFFECTS.register(new Identifier(CreateDimensionsMod.MOD_ID, id),
				() -> _createEffect(effect, category, colour));
		}

		@SuppressWarnings("unchecked")
		public <T extends StatusEffect> StatusEffect _createEffect(Class<T> clazz, StatusEffectCategory category, int colour) {
			try {
				for (Constructor<?> constructor : clazz.getDeclaredConstructors()) {
					if (constructor.getParameterCount() == 2) {
						constructor.setAccessible(true);
						return (T) constructor.newInstance(category, colour);
					}
				}

				throw new NoSuchMethodException("No matching constructor found");
			} catch (Exception e) {
				return new FastFallingEffect(category, colour);
			}
		}
	}
}