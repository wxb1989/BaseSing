package com.cx.sin.utils.base;

/**
 * 16进制ASCII转换工具类
 * @author XuXu
 * @version 2014年11月20日  下午10:23:19
 */
public class StringToAsciiaUtil {

	public static String convertStringToHex(String str) {
		char[] chars = str.toCharArray();

		StringBuffer hex = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			hex.append(Integer.toHexString((int) chars[i]));
		}

		return hex.toString();
	}

	public static String convertHexToString(String hex) {

		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();

		for (int i = 0; i < hex.length() - 1; i += 2) {

			String output = hex.substring(i, (i + 2));
			int decimal = Integer.parseInt(output, 16);
			sb.append((char) decimal);

			temp.append(decimal);
		}

		return sb.toString();
	}
	
	public static String converIntToHex(int v) {
		String hex = "";
		hex = Integer.toHexString(v);
		int length = hex.length();
		if (length == 1 || length == 3) {
			hex = "0" + hex;
		}
		return hex;
	}
}
