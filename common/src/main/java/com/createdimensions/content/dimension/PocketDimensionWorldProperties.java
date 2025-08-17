package com.createdimensions.content.dimension;

import com.createdimensions.mixin.accessors.GameRulesAccessor;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.level.ServerWorldProperties;
import net.minecraft.world.timer.Timer;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class PocketDimensionWorldProperties implements ServerWorldProperties {
	WorldBorder.Properties worldBorder;
	boolean initialized = false;

	@Override
	public String getLevelName() {
		return "Pocket Dimension";
	}

	@Override
	public void setThundering(boolean thundering) {

	}

	@Override
	public int getRainTime() {
		return 0;
	}

	@Override
	public void setRainTime(int rainTime) {

	}

	@Override
	public void setThunderTime(int thunderTime) {

	}

	@Override
	public int getThunderTime() {
		return 0;
	}

	@Override
	public int getClearWeatherTime() {
		return 0;
	}

	@Override
	public void setClearWeatherTime(int clearWeatherTime) {

	}

	@Override
	public int getWanderingTraderSpawnDelay() {
		return 0;
	}

	@Override
	public void setWanderingTraderSpawnDelay(int wanderingTraderSpawnDelay) {

	}

	@Override
	public int getWanderingTraderSpawnChance() {
		return 0;
	}

	@Override
	public void setWanderingTraderSpawnChance(int wanderingTraderSpawnChance) {

	}

	@Override
	public @Nullable UUID getWanderingTraderId() {
		return null;
	}

	@Override
	public void setWanderingTraderId(UUID wanderingTraderId) {

	}

	@Override
	public GameMode getGameMode() {
		return GameMode.DEFAULT;
	}

	@Override
	public void setWorldBorder(WorldBorder.Properties worldBorder) {
		this.worldBorder = worldBorder;
	}

	@Override
	public WorldBorder.Properties getWorldBorder() {
		return worldBorder;
	}

	@Override
	public boolean isInitialized() {
		return initialized;
	}

	@Override
	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	@Override
	public boolean areCommandsAllowed() {
		return false;
	}

	@Override
	public void setGameMode(GameMode gameMode) {

	}

	@Override
	public Timer<MinecraftServer> getScheduledEvents() {
		return null;
	}

	@Override
	public void setTime(long time) {

	}

	@Override
	public void setTimeOfDay(long timeOfDay) {

	}

	@Override
	public void setSpawnX(int spawnX) {

	}

	@Override
	public void setSpawnY(int spawnY) {

	}

	@Override
	public void setSpawnZ(int spawnZ) {

	}

	@Override
	public void setSpawnAngle(float spawnAngle) {

	}

	@Override
	public int getSpawnX() {
		return 0;
	}

	@Override
	public int getSpawnY() {
		return 64;
	}

	@Override
	public int getSpawnZ() {
		return 0;
	}

	@Override
	public float getSpawnAngle() {
		return 0;
	}

	@Override
	public long getTime() {
		return 0;
	}

	@Override
	public long getTimeOfDay() {
		return 0;
	}

	@Override
	public boolean isThundering() {
		return false;
	}

	@Override
	public boolean isRaining() {
		return false;
	}

	@Override
	public void setRaining(boolean raining) {

	}

	@Override
	public boolean isHardcore() {
		return true;
	}

	@Override
	public GameRules getGameRules() {
		@SuppressWarnings({"unchecked", "rawtypes"}) Map<GameRules.Key<?>, GameRules.Rule<?>> rules =
			GameRulesAccessor.getRuleTypes().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
				(e) -> ((GameRules.Type) e.getValue()).createRule()));

		rules.put(GameRules.KEEP_INVENTORY, GameRules.BooleanRule.create(false).createRule());
		rules.put(GameRules.DO_DAYLIGHT_CYCLE, GameRules.BooleanRule.create(false).createRule());
		rules.put(GameRules.DO_WEATHER_CYCLE, GameRules.BooleanRule.create(false).createRule());
		rules.put(GameRules.DO_INSOMNIA, GameRules.BooleanRule.create(true).createRule());
		rules.put(GameRules.DO_IMMEDIATE_RESPAWN, GameRules.BooleanRule.create(true).createRule());
		rules.put(GameRules.GLOBAL_SOUND_EVENTS, GameRules.BooleanRule.create(false).createRule());

		return GameRulesAccessor.create(rules);
	}

	@Override
	public Difficulty getDifficulty() {
		return Difficulty.HARD;
	}

	@Override
	public boolean isDifficultyLocked() {
		return true;
	}
}
