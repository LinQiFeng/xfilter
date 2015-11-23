package com.zero.xfilter.util;

public class Utils {

	/**
	 * 
	 * @description (将num个str进行拼接)
	 * @author      zero
	 * @createTime  2015年05月02日 下午3:29:40
	 * @param str
	 * @param num
	 * @return
	 */
	public static String concat(String str, int num) {
		StringBuilder result = new StringBuilder(str);
		for (int index = 1; index < num; index++) {
			result.append(str);
		}
		return result.toString();
	}
}
