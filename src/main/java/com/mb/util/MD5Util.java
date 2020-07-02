package com.mb.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

import org.apache.commons.lang3.StringUtils;

public class MD5Util {

	public MD5Util() {

	}

	public static String md5(String input) {
		if (StringUtils.isBlank(input)) {
			return "";
		} else {
			byte[] source;
			try {
				source = input.getBytes("UTF-8");
			} catch (UnsupportedEncodingException var10) {
				source = input.getBytes();
			}
			String result = null;
			char[] hexDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

			try {
				MessageDigest e = MessageDigest.getInstance("MD5");
				e.update(source);
				byte[] temp = e.digest();
				char[] str = new char[32];
				int k = 0;

				for (int i = 0; i < 16; ++i) {
					byte byte0 = temp[i];
					str[k++] = hexDigits[byte0 >>> 4 & 15];
					str[k++] = hexDigits[byte0 & 15];
				}

				result = new String(str);
			} catch (Exception var11) {
				var11.printStackTrace();
			}
			return result;
		}
	}
}