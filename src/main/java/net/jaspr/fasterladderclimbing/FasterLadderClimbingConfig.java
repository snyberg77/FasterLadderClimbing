package net.jaspr.fasterladderclimbing;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@EventBusSubscriber(modid = FasterLadderClimbing.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class FasterLadderClimbingConfig {

	public static final GeneralConfig CONFIG;
	public static final ForgeConfigSpec CONFIG_SPEC;
	static {
		final Pair<GeneralConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(GeneralConfig::new);
		CONFIG_SPEC = specPair.getRight();
		CONFIG = specPair.getLeft();
	}

	public static int speedModifier;
	public static boolean allowQuickAscension;
	public static boolean allowQuickDescension;

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfigEvent configEvent) {
		if (configEvent.getConfig().getSpec() == FasterLadderClimbingConfig.CONFIG_SPEC) {
			bakeConfig();
		}
	}

	public static void bakeConfig() {
		speedModifier = CONFIG.speedModifier.get();
		allowQuickAscension = CONFIG.allowQuickAscension.get();
		allowQuickDescension = CONFIG.allowQuickDescension.get();
	}

	public static class GeneralConfig {

		public final ForgeConfigSpec.IntValue speedModifier;
		public final ForgeConfigSpec.BooleanValue allowQuickAscension;
		public final ForgeConfigSpec.BooleanValue allowQuickDescension;

		public GeneralConfig (ForgeConfigSpec.Builder builder) {
			builder.push("General");

			allowQuickAscension = builder
				.comment("Allow going UP faster. If [false], then player can only climb up the ladder at normal speed.")
				.define("allowQuickAscension", true);
			allowQuickDescension = builder
				.comment("Allow going DOWN faster. If [false], then player can only climb down the ladder at normal speed.")
				.define("allowQuickDescension", true);
			speedModifier = builder
				.comment("Speed modifier. 0 is Vanilla speed, 10 is lightning speed.")
				.defineInRange("speedModifier", 4, 0, 10);

			builder.pop();
		}
	}	
}