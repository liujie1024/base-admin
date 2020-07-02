package com.mb.test.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mb.test.dao.ConfigureDao;
import com.mb.test.entity.Configure;
import com.mb.test.service.IConfigureService;

/**
 * 投诉接口实现
 */
@Service
public class ConfigureServiceImpl implements IConfigureService {

	@Autowired
	private ConfigureDao configureDao;// 投诉配置

	@Override
	public List<Configure> selectByParamListPage(Map<String, Object> paramMap) {
		return configureDao.selectByParamListPage(paramMap);
	}

}
