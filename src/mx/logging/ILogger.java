package mx.logging;

import alde.flash.utils.Vector;

public class ILogger {
	public static ILogger instance = new ILogger();

	public static void info(String info) {
		System.out.println(info);
	}

	public static ILogger getInstance() {
		return instance;
	}

	public void warn(String s, Vector<String> strings) {
	}
}
