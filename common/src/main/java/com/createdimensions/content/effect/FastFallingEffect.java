package com.createdimensions.content.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class FastFallingEffect extends StatusEffect {
	public FastFallingEffect(StatusEffectCategory category, int color) {
		super(category, color);
	}

	@Override
	public void applyUpdateEffect(LivingEntity entity, int amplifier) {
		entity.addVelocity(0, -0.2 * amplifier, 0);
		super.applyUpdateEffect(entity, amplifier);
	}

	@Override
	public boolean canApplyUpdateEffect(int duration, int amplifier) {
		return true;
	}
}
