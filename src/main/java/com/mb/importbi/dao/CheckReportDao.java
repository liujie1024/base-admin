package com.mb.importbi.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.base.BaseDao;
import com.mb.importbi.entity.CheckReport;

@MapperScan
public interface CheckReportDao extends BaseDao<CheckReport, java.lang.String> {

	/**
	 * MB直营品牌，应该上传的分公司list
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CheckReport> getMBList4Zy(Map<String, Object> paramMap);

	/**
	 * MB合伙人品牌，应该上传的分公司list
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CheckReport> getMBList4Hhr(Map<String, Object> paramMap);

	/**
	 * MM合伙人品牌，应该上传的分公司list
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CheckReport> getMMList4Hhr(Map<String, Object> paramMap);

	/**
	 * 分类获得总数，根据品牌和属性分类
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CheckReport> getCountByBrandAndClass(Map<String, Object> paramMap);

	/**
	 * 加盟，只要有数据就代表加盟已经导入
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<CheckReport> getJmscCount(Map<String, Object> paramMap);

}