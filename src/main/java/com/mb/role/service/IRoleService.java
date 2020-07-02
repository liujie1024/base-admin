package com.mb.role.service;

import java.util.List;

import com.mb.base.BaseService;
import com.mb.role.entity.Role;

public interface IRoleService extends BaseService<Role, java.lang.String> {

	/**
	 * 
	 * 根据用户名查询对应角色
	 * 
	 * @param username
	 *            用户名
	 * @return List<Role>
	 *
	 */
	public List<Role> getRolesByUsername(String username);

	/**
	 * 查询该角色名称是否存在
	 * 
	 * @param role
	 *            角色对象
	 * @return
	 */
	public boolean isHasRolename(Role role);

	/**
	 * 根据角色插入对应的菜单
	 * 
	 * @param roleId
	 *            角色id
	 * @param menuList
	 *            菜单id的list
	 */
	public void insertRoleMenu(String roleId, List<String> menuList);

}
