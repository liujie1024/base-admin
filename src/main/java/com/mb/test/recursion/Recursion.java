package com.mb.test.recursion;

/**
 * 递归test
 * 
 * @author LiuJie
 *
 */
public class Recursion {

	/**
	 * 一列数的规则如下: 1、1、2、3、5、8、13、21、34 ，求第30位数是多少？使用递归实现
	 * 
	 * @param n
	 *            第几位
	 * @return
	 */
	private static int testRecursion(int n) {
		if (n <= 2) {
			return 1;
		} else {
			return testRecursion(n - 1) + testRecursion(n - 2);
		}
	}

	public static void main(String[] args) {
		System.out.println(testRecursion(30));
	}

}
