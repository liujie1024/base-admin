package com.mb.base;

import java.util.List;
import java.util.Map;

public interface BaseService<T, K> {

	/**
	 * 保存数据
	 * 
	 * @param entity
	 * @return
	 */
	public boolean save(T entity);

	/**
	 * 更新数据
	 * 
	 * @param entity
	 * @return
	 */
	public boolean update(T entity);

	/**
	 * 根据主键删除
	 * 
	 * @param id
	 * @return
	 */
	public boolean deleteById(K id);

	/**
	 * 根据主键查询
	 * 
	 * @param id
	 * @return
	 */
	public T findById(K id);

	/**
	 * 根据参数分页查询数据
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<T> getListByParam(Map<String, Object> paramMap);

}
