package com.mb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 校验一下数字，小数等
 */
public class NumberValidationUtil {

	private static boolean isMatch(String regex, String orginal) {
		if (orginal == null || orginal.trim().equals("")) {
			return false;
		}
		Pattern pattern = Pattern.compile(regex);
		Matcher isNum = pattern.matcher(orginal);
		return isNum.matches();
	}

	/**
	 * 判断是否为正整数，不包括0，10，20等
	 * 
	 * @param orginal
	 * @return
	 */
	public static boolean isPositiveInteger(String orginal) {
		return isMatch("^\\+{0,1}[1-9]\\d*", orginal);
	}

	/**
	 * 判断是否为正整数，包括0，10，20等
	 * 
	 * @param orginal
	 * @return
	 */
	public static boolean isPositiveInteger2(String orginal) {
		return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal);
	}

	/**
	 * 判断为负整数
	 * 
	 * @param orginal
	 * @return
	 */
	public static boolean isNegativeInteger(String orginal) {
		return isMatch("^-[1-9]\\d*", orginal);
	}

	/**
	 * 判断是否为正负整数
	 * 
	 * @param orginal
	 * @return
	 */
	public static boolean isWholeNumber(String orginal) {
		return isMatch("[+-]{0,1}0", orginal) || isPositiveInteger(orginal) || isNegativeInteger(orginal);
	}

	/**
	 * 判断是否为正小数
	 * 
	 * @param orginal
	 * @return
	 */
	public static boolean isPositiveDecimal(String orginal) {
		return isMatch("\\+{0,1}[0]\\.[1-9]*|\\+{0,1}[1-9]\\d*\\.\\d*", orginal);
	}

	/**
	 * 判断是否为负小数
	 * 
	 * @param orginal
	 * @return
	 */
	public static boolean isNegativeDecimal(String orginal) {
		return isMatch("^-[0]\\.[1-9]*|^-[1-9]\\d*\\.\\d*", orginal);
	}

	/**
	 * 判断是否为正负小数
	 * 
	 * @param orginal
	 * @return
	 */
	public static boolean isDecimal(String orginal) {
		return isMatch("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+", orginal);
	}

	/**
	 * 是否为数字，可能为整数，可能为小数
	 * 
	 * @param orginal
	 * @return
	 */
	public static boolean isRealNumber(String orginal) {
		return isWholeNumber(orginal) || isDecimal(orginal);
	}

	public static void main(String[] args) {
		System.out.println(isPositiveInteger2("zho"));
	}
}
