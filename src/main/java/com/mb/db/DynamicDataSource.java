package com.mb.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 写一个DynamicDataSource类来继承AbstractRoutingDataSource，
 * 并重写determineCurrentLookupKey（）方法，来达到动态切换数据库
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		return DataSourceContextHolder.getDbType();
	}

}
