package com.mb.importbi.service;

import java.util.List;

import com.mb.base.BaseService;
import com.mb.importbi.entity.CheckReport;

public interface ICheckReportService extends BaseService<CheckReport, java.lang.String> {

	/**
	 * MB直营品牌，应该上传的分公司list
	 * 
	 * @return
	 */
	public List<CheckReport> getMBList4Zy();

	/**
	 * MB合伙人品牌，应该上传的分公司list
	 * 
	 * @return
	 */
	public List<CheckReport> getMBList4Hhr();

	/**
	 * MM合伙人品牌，应该上传的分公司list
	 * 
	 * @return
	 */
	public List<CheckReport> getMMList4Hhr();

	/**
	 * 分类获得总数，根据品牌和属性分类
	 * 
	 * @param shopBrandName
	 *            品牌名称，MB等
	 * @param shopClassName
	 *            店铺属性，直营，合伙等
	 * @return
	 */
	public List<CheckReport> getCountByBrandAndClass(String shopBrandName, String shopClassName);

	/**
	 * 获得加盟市场的导入数据个数，只要有数据，就表示已经导入了
	 * 
	 * @return
	 */
	public List<CheckReport> getJmscCount();

}
