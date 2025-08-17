package com.createdimensions.mixin.accessors;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.Map;

@Mixin(GameRules.class)
public interface GameRulesAccessor {
	@Accessor("RULE_TYPES")
	static Map<GameRules.Key<?>, GameRules.Type<?>> getRuleTypes() {
		throw new AssertionError();
	};

	@Invoker("<init>")
	static GameRules create(Map<GameRules.Key<?>, GameRules.Rule<?>> rules) {
		throw new AssertionError();
	}
}
