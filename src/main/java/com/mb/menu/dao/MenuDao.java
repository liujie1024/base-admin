package com.mb.menu.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.base.BaseDao;
import com.mb.menu.entity.Menu;

@MapperScan
public interface MenuDao extends BaseDao<Menu, java.lang.String> {

	/**
	 * 
	 * 根据角色id查询对应的菜单
	 * 
	 * @param roleId
	 *            角色id
	 * @return List<Role>
	 *
	 */
	public List<Menu> getMenuListByRoleId(String roleId);

	/**
	 * 根据父菜单id查询下面的子菜单
	 * 
	 * @param parentId
	 *            父菜单的id
	 * @return
	 */
	public List<Menu> getMenuListByParentId(String parentId);

	/**
	 * 查询所有的菜单
	 * 
	 * @return
	 */
	public List<Menu> getAllMenuList(Map<String, Object> paramMap);

	/**
	 * 查询所有的父菜单
	 * 
	 * @return
	 */
	public List<Menu> getParentMenuList(Map<String, Object> paramMap);

}
