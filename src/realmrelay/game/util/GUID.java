package realmrelay.game.util;

public class UID {

	private static Number counter = 0;

	public GUID() {
		super();
	}

	public static  String   create()  {
         Date loc1 = new Date();
         Number loc2 = loc1.getTime();
         Number loc3 = Math.random() * double.MAXVALUE;
         String loc4 = Capabilities.serverString;
        return calculate(loc2 + loc4 + loc3 + counter++).toUpperCase();
    }

	private static String calculate(String param1) {
		return hexsha1(param1);
	}

	private static String hexsha1(String param1) {
		return binb2hex(coresha1(str2binb(param1), param1.length * 8));
	}

	private static Array coresha1(Array param1, Number param2) {
		Number loc10 = NaN;
		Number loc11 = NaN;
		Number loc12 = NaN;
		Number loc13 = NaN;
		Number loc14 = NaN;
		Number loc15 = NaN;
		Number loc16 = NaN;
		param1[param2 >> 5] = param1[param2 >> 5] | 128 << 24 - param2 % 32;
		param1[(param2 + 64 >> 9 << 4) + 15] = param2;
		Array loc3 = new Array(80);
		Number loc4 = 1732584193;
		Number loc5 = -271733879;
		Number loc6 = -1732584194;
		Number loc7 = 271733878;
		Number loc8 = -1009589776;
		Number loc9 = 0;
		while (loc9 < param1.length) {
			loc10 = loc4;
			loc11 = loc5;
			loc12 = loc6;
			loc13 = loc7;
			loc14 = loc8;
			loc15 = 0;
			while (loc15 < 80) {
				if (loc15 < 16) {
					loc3[loc15] = param1[loc9 + loc15];
				} else {
					loc3[loc15] = rol(loc3[loc15 - 3] ^ loc3[loc15 - 8] ^ loc3[loc15 - 14] ^ loc3[loc15 - 16], 1);
				}
				loc16 = safeadd(safeadd(rol(loc4, 5), sha1ft(loc15, loc5, loc6, loc7)),
						safeadd(safeadd(loc8, loc3[loc15]), sha1kt(loc15)));
				loc8 = loc7;
				loc7 = loc6;
				loc6 = rol(loc5, 30);
				loc5 = loc4;
				loc4 = loc16;
				loc15++;
			}
			loc4 = safeadd(loc4, loc10);
			loc5 = safeadd(loc5, loc11);
			loc6 = safeadd(loc6, loc12);
			loc7 = safeadd(loc7, loc13);
			loc8 = safeadd(loc8, loc14);
			loc9 = loc9 + 16;
		}
		return new Array(loc4, loc5, loc6, loc7, loc8);
	}

	private static Number sha1ft(Number param1, Number param2, Number param3, Number param4) {
		if (param1 < 20) {
			return param2 & param3 | ~param2 & param4;
		}
		if (param1 < 40) {
			return param2 ^ param3 ^ param4;
		}
		if (param1 < 60) {
			return param2 & param3 | param2 & param4 | param3 & param4;
		}
		return param2 ^ param3 ^ param4;
	}

	private static Number sha1kt(Number param1) {
		return param1 < 20 ? 1518500249 : param1 < 40 ? 1859775393 : param1 < 60 ? -1894007588 : -899497514;
	}

	private static Number safeadd(Number param1, Number param2) {
		Number loc3 = (param1 & 65535) + (param2 & 65535);
		Number loc4 = (param1 >> 16) + (param2 >> 16) + (loc3 >> 16);
		return loc4 << 16 | loc3 & 65535;
	}

	private static Number rol(Number param1, Number param2) {
		return param1 << param2 | param1 >>> 32 - param2;
	}

	private static Array str2binb(String param1) {
		Array loc2 = new Array();
		Number loc3 = 1 << 8 - 1;
		Number loc4 = 0;
		while (loc4 < param1.length * 8) {
			loc2[loc4 >> 5] = loc2[loc4 >> 5] | (param1.charCodeAt(loc4 / 8) & loc3) << 24 - loc4 % 32;
			loc4 = loc4 + 8;
		}
		return loc2;
	}

	private static String binb2hex(Array param1) {
		String loc2 = new String("");
		String loc3 = new String("0123456789abcdef");
		Number loc4 = 0;
		while (loc4 < param1.length * 4) {
			loc2 = loc2 + (loc3.charAt(param1[loc4 >> 2] >> (3 - loc4 % 4) * 8 + 4 & 15)
					+ loc3.charAt(param1[loc4 >> 2] >> (3 - loc4 % 4) * 8 & 15));
			loc4++;
		}
		return loc2;
	}
}
