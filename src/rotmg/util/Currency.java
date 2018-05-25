package rotmg.util;

public class Currency {

	public static final int INVALID = -1;

	public static final int GOLD = 0;

	public static final int FAME = 1;

	public static final int GUILD_FAME = 2;

	public static final int FORTUNE = 3;

	public Currency() {
		super();
	}

	public static String typeToName(int param1) {
		switch (param1) {
			case GOLD:
				return "Gold";
			case FAME:
				return "Fame";
			case GUILD_FAME:
				return "Guild Fame";
			case FORTUNE:
				return "Fortune Token";
			default:
				return "";
		}
	}


}
