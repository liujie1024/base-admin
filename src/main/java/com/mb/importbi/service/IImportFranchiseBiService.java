package com.mb.importbi.service;

import java.util.List;
import java.util.Map;

import com.mb.base.BaseService;
import com.mb.importbi.entity.ImportFranchiseBi;

public interface IImportFranchiseBiService extends BaseService<ImportFranchiseBi, java.lang.String> {

	/**
	 * 批量插入数据
	 * 
	 * @param importFranchiseBiList
	 *            批量数据
	 */
	public void insertBatch(List<ImportFranchiseBi> importFranchiseBiList);

	/**
	 * 批量插入数据,方法2
	 * 
	 * @param batchMap
	 *            批量数据
	 */
	public void insertBatchByMap(Map<String, Object> batchMap);

	/**
	 * 删除数据
	 * 
	 * @param deleteMap
	 *            数据
	 */
	public void deleteByMap(Map<String, Object> deleteMap);

	/**
	 * 批量删除数据
	 * 
	 * @param replaceRowList
	 *            批量数据
	 */
	public void deleteBatchByMap(List<String> replaceRowList);

	/**
	 * 根据市场ID，8位码以及门店定位查询数据
	 * 
	 * @param importFranchiseBi
	 *            导入行对象
	 * @return true：有数据；false：无数据
	 */
	public boolean isHasRow(ImportFranchiseBi importFranchiseBi);

}
