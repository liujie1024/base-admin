package com.mb.test.service;

import java.util.List;
import java.util.Map;

import com.mb.test.entity.Configure;


/**
 * 权限配置服务接口
 */
public interface IConfigureService {

	/**
	 * 根据用户名称查询数据
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<Configure> selectByParamListPage(Map<String, Object> paramMap);

}
