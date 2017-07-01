package com.oversea.api.util;

public class NvshenStringUtil {
	
	/**
	 * 字符串是否为纯数字
	 * @param str
	 * @return
	 */
	public static boolean isNumberOnly(String str) {
		return str.matches("[0-9]+");
	}

}
