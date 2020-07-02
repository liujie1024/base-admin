package com.mb.importbi.service;

import java.util.List;
import java.util.Map;

import com.mb.base.BaseService;
import com.mb.importbi.entity.ImportBi;

public interface IImportBiService extends BaseService<ImportBi, java.lang.String> {

	/**
	 * 批量插入数据
	 * 
	 * @param importBiList
	 *            批量数据
	 */
	public void insertBatch(List<ImportBi> importBiLists) throws Exception;

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
	 * 根据门店ID，8位码以及门店定位查询数据
	 * 
	 * @param importBi
	 *            导入行对象
	 * @return true：有数据；false：无数据
	 */
	public boolean isHasRow(ImportBi importBi);

}
