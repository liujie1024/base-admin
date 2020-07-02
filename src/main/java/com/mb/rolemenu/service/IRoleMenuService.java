package com.mb.rolemenu.service;

import java.util.List;

import com.mb.base.BaseService;
import com.mb.rolemenu.entity.RoleMenu;

public interface IRoleMenuService extends BaseService<RoleMenu, java.lang.String> {

	/**
	 * 根据角色id查询对应的菜单
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	public List<RoleMenu> selectRoleMenuListByRoleId(String roleId);

}
