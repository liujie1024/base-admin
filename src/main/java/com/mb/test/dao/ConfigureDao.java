package com.mb.test.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.test.entity.Configure;

/**
 * 权限配置dao
 */
@MapperScan
public interface ConfigureDao {

	/**
	 * 根据用户名称查询数据
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<Configure> selectByParamListPage(Map<String, Object> paramMap);

}
