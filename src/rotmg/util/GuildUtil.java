package rotmg.util;

import flash.display.BitmapData;

public class GuildUtil {


	public static final int INITIATE = 0;

	public static final int MEMBER = 10;

	public static final int OFFICER = 20;

	public static final int LEADER = 30;

	public static final int FOUNDER = 40;

	public static final int MAX_MEMBERS = 50;


	public GuildUtil() {
		super();
	}

	public static String rankToString(int param1) {
		switch (param1) {
			case INITIATE:
				return wrapInBraces(TextKey.GUILD_RANK_INITIATE);
			case MEMBER:
				return wrapInBraces(TextKey.GUILD_RANK_MEMBER);
			case OFFICER:
				return wrapInBraces(TextKey.GUILD_RANK_OFFICER);
			case LEADER:
				return wrapInBraces(TextKey.GUILD_RANK_LEADER);
			case FOUNDER:
				return wrapInBraces(TextKey.GUILD_RANK_FOUNDER);
			default:
				return wrapInBraces(TextKey.GUILD_RANK_UNKNOWN);
		}
	}

	private static String wrapInBraces(String param1) {
		return "{" + param1 + "}";
	}

	public static BitmapData rankToIcon(int param1, int param2) {
		BitmapData loc3 = null;
		switch (param1) {
			case INITIATE:
				loc3 = AssetLibrary.getImageFromSet("lofiInterfaceBig", 20);
				break;
			case MEMBER:
				loc3 = AssetLibrary.getImageFromSet("lofiInterfaceBig", 19);
				break;
			case OFFICER:
				loc3 = AssetLibrary.getImageFromSet("lofiInterfaceBig", 18);
				break;
			case LEADER:
				loc3 = AssetLibrary.getImageFromSet("lofiInterfaceBig", 17);
				break;
			case FOUNDER:
				loc3 = AssetLibrary.getImageFromSet("lofiInterfaceBig", 16);
		}
		return TextureRedrawer.redraw(loc3, param2, true, 0, true);
	}

	public static BitmapData guildFameIcon(int param1) {
		BitmapData loc2 = AssetLibrary.getImageFromSet("lofiObj3", 226);
		return TextureRedrawer.redraw(loc2, param1, true, 0, true);
	}

	public static boolean allowedChange(int param1, int param2, int param3) {
		if (param2 == param3) {
			return false;
		}
		if (param1 == FOUNDER && param2 < FOUNDER && param3 < FOUNDER) {
			return true;
		}
		if (param1 == LEADER && param2 < LEADER && param3 <= LEADER) {
			return true;
		}
		if (param1 == OFFICER && param2 < OFFICER && param3 < OFFICER) {
			return true;
		}
		return false;
	}

	public static int promotedRank(int param1) {
		switch (param1) {
			case INITIATE:
				return MEMBER;
			case MEMBER:
				return OFFICER;
			case OFFICER:
				return LEADER;
			default:
				return FOUNDER;
		}
	}

	public static boolean canPromote(int param1, int param2) {
		int loc3 = promotedRank(param2);
		return allowedChange(param1, param2, loc3);
	}

	public static int demotedRank(int param1) {
		switch (param1) {
			case OFFICER:
				return MEMBER;
			case LEADER:
				return OFFICER;
			case FOUNDER:
				return LEADER;
			default:
				return INITIATE;
		}
	}

	public static boolean canDemote(int param1, int param2) {
		int loc3 = demotedRank(param2);
		return allowedChange(param1, param2, loc3);
	}

	public static boolean canRemove(int param1, int param2) {
		return param1 >= OFFICER && param2 < param1;
	}


}
