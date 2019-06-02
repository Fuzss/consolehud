package com.fuzs.consolehud.handler;

import com.fuzs.consolehud.ConsoleHud;
import com.fuzs.consolehud.util.EnumPositionPreset;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = ConsoleHud.MODID)
@Mod.EventBusSubscriber
public class ConfigHandler {

	@Config.Name("helditemtooltips")
	public static SelectedItemConfig heldItemTooltipsConfig = new SelectedItemConfig();

	@Config.Name("paperdoll")
	public static PaperDollConfig paperDollConfig = new PaperDollConfig();

	@Config.Name("hoveringhotbar")
	public static HoveringHotbarConfig hoveringHotbarConfig = new HoveringHotbarConfig();

	@Config.Name("saveicon")
	public static SaveIconConfig saveIconConfig = new SaveIconConfig();

	@Config.Name("Held Item Tooltips")
	@Config.Comment("Enhances vanilla held item tooltips with information about enchantments, potions effects, shulker box contents, and more.")
	public static boolean heldItemTooltips = true;

	@Config.Name("Paper Doll")
	@Config.Comment("Shows a small player model in a configurable corner of the screen while the player is sprinting, sneaking, or flying.")
	public static boolean paperDoll = true;

	@Config.Name("Hovering Hotbar")
	@Config.Comment("Enables the hotbar to hover anywhere on the screen. By default just moves it up a little from the screen bottom.")
	public static boolean hoveringHotbar = true;

	@Config.Name("Save Icon")
	@Config.Comment("Enables the hotbar to hover anywhere on the screen. By default just moves it up a little from the screen bottom.")
	public static boolean saveIcon = true;

	public static class SelectedItemConfig {

		@Config.Name("appearance")
		public AppearanceConfig appearanceConfig = new AppearanceConfig();

		@Config.Name("Blacklist")
		@Config.Comment("Disables held item tooltips for specified items and mods, mainly to prevent custom tooltips from overlapping.")
		public String[] blacklist = new String[]{"psi:cad", "psi:psimetal_shovel", "psi:psimetal_pickaxe", "psi:psimetal_axe", "psi:psimetal_exosuit_helmet", "psi:psimetal_exosuit_chestplate", "psi:psimetal_exosuit_leggings", "psi:psimetal_exosuit_boots"};

		@Config.Name("Rows")
		@Config.Comment("Maximum amount of rows to be displayed for held item tooltips.")
		@Config.RangeInt(min = 2, max = 7)
		@Config.SlidingOption
		public int rows = 5;

		@Config.Name("Display Time")
		@Config.Comment("Amount of ticks the held item tooltip will be displayed for.")
		@Config.RangeInt(min = 0)
		public int displayTime = 40;

		@Config.Name("X-Offset")
		@Config.Comment("Offset on x-axis from screen center.")
		@Config.RangeInt()
		public int xOffset = 0;

		@Config.Name("Y-Offset")
		@Config.Comment("Offset on y-axis from screen bottom.")
		@Config.RangeInt(min = 0)
		public int yOffset = 59;

		public static class AppearanceConfig {

			@Config.Name("Show Modded Tooltips")
			@Config.Comment("Enables tooltip information added by other mods like Hwyla to be displayed as a held item tooltip.")
			public boolean moddedTooltips = false;

			@Config.Name("Show Durability")
			@Config.Comment("Displays the item's durability as part of its held item tooltip (only when enough space is available).")
			public boolean durabilityShow = true;

			@Config.Name("Show Last Line")
			@Config.Comment("Show how many more lines there are that don't fit the tooltip.")
			public boolean lastLineShow = true;

			@Config.Name("Sum Shulker Box Contents")
			@Config.Comment("Sum up stacks of equal items in a shulker box. Only affects the inventory tooltip, held item tooltips always use this.")
			public boolean sumShulkerBox = true;

			@Config.Name("Last Line Format")
			@Config.Comment("Define a custom format to be used for the last line of a tooltip when there are more lines than there is space. Leave this empty for the default, translatable string. Use %s (up to one time) in your custom format to include the amount of cut off lines.")
			public String lastLineFormat = "";

			@Config.Name("Durability Format")
			@Config.Comment("Define a custom format to be used for the durability line. Leave this empty for the default, translatable string. Use %s (up to two times) to include remaining uses and total uses in your custom format. \"Show Durability\" has to be enabled for this to have any effect.")
			public String durabilityFormat = "";

		}

	}

	public static class PaperDollConfig {

		@Config.Name("displayactions")
		public DisplayActionsConfig displayActionsConfig = new DisplayActionsConfig();

		@Config.Name("Screen Corner Old")
		@Config.Comment("Defines a screen corner to display the paper doll in. [0: top left, 1: bottom left, 2: top right, 3: bottom right, default: 0]")
		@Config.RangeInt(min = 0, max = 3)
		public int oldPosition = 0;

		@Config.Name("Screen Corner")
		@Config.Comment("Defines a screen corner to display the paper doll in.")
		public EnumPositionPreset position = EnumPositionPreset.TOP_LEFT;

		@Config.Name("Scale")
		@Config.Comment("Scale of the paper doll. This is additionally adjusted by the GUI Scale option in Video Settings.")
		@Config.RangeInt(min = 1, max = 24)
		@Config.SlidingOption
		public int scale = 4;

		@Config.Name("X-Offset")
		@Config.Comment("Offset on x-axis from original doll position.")
		@Config.RangeInt()
		public int xOffset = 0;

		@Config.Name("Y-Offset")
		@Config.Comment("Offset on y-axis from original doll position.")
		@Config.RangeInt()
		public int yOffset = 0;

		@Config.Name("Display Time")
		@Config.Comment("Amount of ticks the paper doll will be kept on screen after its conditions are no longer met.")
		@Config.RangeInt(min = 0)
		public int displayTime = 20;

		public static class DisplayActionsConfig {

			@Config.Name("Always")
			@Config.Comment("Always displays the paper doll, no matter what action the player is performing.")
			public boolean always = false;

			@Config.Name("Sprinting")
			@Config.Comment("Enables the paper doll while the player is sprinting.")
			public boolean sprinting = true;

			@Config.Name("Crouching")
			@Config.Comment("Enables the paper doll while the player is crouching.")
			public boolean crouching = true;

			@Config.Name("Flying")
			@Config.Comment("Displays the paper doll when the player is using creative mode flight.")
			public boolean flying = true;

			@Config.Name("Elytra Flying")
			@Config.Comment("Shows the paper doll while the player is flying with an elytra.")
			public boolean elytraFlying = true;

			@Config.Name("Burning")
			@Config.Comment("Disables flame overlay on the hud when on fire and displays the burning paper doll instead.")
			public boolean burning = false;

			@Config.Name("Riding")
			@Config.Comment("Shows the paper doll while the player is riding any entity.")
			public boolean riding = false;

		}

	}

	public static class HoveringHotbarConfig {

		@Config.Name("X-Offset")
		@Config.Comment("Offset on x-axis from screen center.")
		@Config.RangeInt()
		public int xOffset = 0;

		@Config.Name("Y-Offset")
		@Config.Comment("Offset on y-axis from screen bottom.")
		@Config.RangeInt(min = 0)
		public int yOffset = 18;

		@Config.Name("Mod Compatibility")
		@Config.Comment("Attempt to be compatible with dysfunctional mods. Only enable this when modded hud elements aren't moved together with the hotbar when they should be.")
		public boolean modCompat = false;

	}

	public static class SaveIconConfig {

		@Config.Name("X-Offset")
		@Config.Comment("Offset on x-axis from screen border.")
		@Config.RangeInt()
		public int xOffset = 17;

		@Config.Name("Y-Offset")
		@Config.Comment("Offset on y-axis from screen border.")
		@Config.RangeInt()
		public int yOffset = 15;

		@Config.Name("Screen Corner")
		@Config.Comment("Defines a screen corner to display the save icon in.")
		public EnumPositionPreset position = EnumPositionPreset.TOP_RIGHT;

		@Config.Name("Display Time")
		@Config.Comment("Amount of ticks the save icon will be displayed for.")
		@Config.RangeInt(min = 0)
		public int displayTime = 40;

		@Config.Name("Potion Shift")
		@Config.Comment("Shift the save icon downwards when it would otherwise overlap with the potion icons. Only applicable when the \"Screen Corner\" is set to \"topright\".")
		public boolean potionShift = true;

	}

	@SubscribeEvent
	public static void configChanged(ConfigChangedEvent.OnConfigChangedEvent evt) {
		if (evt.getModID().equals(ConsoleHud.MODID)) {
			ConfigManager.sync(ConsoleHud.MODID, Type.INSTANCE);
		}
	}
	
}