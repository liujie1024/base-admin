package com.mb.menu.service;

import java.util.List;
import java.util.Map;

import com.mb.base.BaseService;
import com.mb.menu.entity.Menu;

public interface IMenuService extends BaseService<Menu, java.lang.String> {

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

	/**
	 * 该菜单名在数据库中是否存在
	 * 
	 * @param menu
	 *            菜单
	 * @return
	 */
	public boolean isHasMenuByName(Menu menu);

}
