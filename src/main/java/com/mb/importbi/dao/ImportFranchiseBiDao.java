package com.mb.importbi.dao;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.base.BaseDao;
import com.mb.importbi.entity.ImportFranchiseBi;

@MapperScan
public interface ImportFranchiseBiDao extends BaseDao<ImportFranchiseBi, java.lang.String> {

	/**
	 * 批量插入数据
	 * 
	 * @param roleMenuMap
	 */
	public void insertBatch(Map<String, Object> insertBatchMap);

	/**
	 * 根据参数，删除数据
	 * 
	 * @param deleteMap
	 */
	public void deleteByMap(Map<String, Object> deleteMap);

	/**
	 * 根据参数，批量删除数据
	 * 
	 * @param deleteBatchMap
	 */
	public void deleteBatchByMap(Map<String, Object> deleteBatchMap);

}