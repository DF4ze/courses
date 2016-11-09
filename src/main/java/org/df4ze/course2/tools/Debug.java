package org.df4ze.course2.tools;

public class Debug {

	private static boolean enable = true;
	
	private Debug() {
		// TODO Auto-generated constructor stub
	}

	public static boolean isEnable() {
		return enable;
	}

	public static void setEnable(boolean enable) {
		Debug.enable = enable;
	}

}
