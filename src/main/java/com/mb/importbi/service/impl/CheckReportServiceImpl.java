package com.mb.importbi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mb.importbi.dao.CheckReportDao;
import com.mb.importbi.entity.CheckReport;
import com.mb.importbi.service.ICheckReportService;

@Service
public class CheckReportServiceImpl implements ICheckReportService {

	private final static Logger LOGGER = LoggerFactory.getLogger(CheckReportServiceImpl.class);

	@Resource
	private CheckReportDao checkReportDao;

	@Override
	public boolean save(CheckReport entity) {
		return false;
	}

	@Override
	public boolean update(CheckReport entity) {
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public CheckReport findById(String id) {
		return null;
	}

	@Override
	public List<CheckReport> getListByParam(Map<String, Object> paramMap) {
		return null;
	}

	@Override
	public List<CheckReport> getMBList4Zy() {
		LOGGER.info("MB直营品牌，应该上传的分公司list");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return checkReportDao.getMBList4Zy(paramMap);
	}

	@Override
	public List<CheckReport> getMBList4Hhr() {
		LOGGER.info("MB合伙人品牌，应该上传的分公司list");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return checkReportDao.getMBList4Hhr(paramMap);
	}

	@Override
	public List<CheckReport> getMMList4Hhr() {
		LOGGER.info("MM合伙人品牌，应该上传的分公司list");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return checkReportDao.getMMList4Hhr(paramMap);
	}

	@Override
	public List<CheckReport> getCountByBrandAndClass(String shopBrandName, String shopClassName) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("shop_brand_name", shopBrandName);// 品牌名称，MB等
		paramMap.put("shop_class_name", shopClassName);// 店铺属性，直营，合伙等
		List<CheckReport> shopWhExtList = checkReportDao.getCountByBrandAndClass(paramMap);
		return shopWhExtList;
	}

	@Override
	public List<CheckReport> getJmscCount() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		return checkReportDao.getJmscCount(paramMap);
	}

}