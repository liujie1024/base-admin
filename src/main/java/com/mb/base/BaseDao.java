package com.mb.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T, K> {

	/**
	 * 保存数据
	 * 
	 * @param entity
	 * @return
	 */
	public int save(T entity);

	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return
	 */
	public int update(T entity);

	/**
	 * 更加主键删除
	 * 
	 * @param id
	 * @return
	 */
	public int deleteById(K id);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	public T findById(K id);

	/**
	 * 根据参数查询
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<T> getListByParam(Map<String, Object> paramMap);
}
