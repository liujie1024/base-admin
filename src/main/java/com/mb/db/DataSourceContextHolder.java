package com.mb.db;

/**
 * 数据库切换的工具类
 */
public class DataSourceContextHolder {

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDbType() {
		return ((String) contextHolder.get());
	}

	public static void clearDbType() {
		contextHolder.remove();
	}

}
