package com.mb.rolemenu.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.base.BaseDao;
import com.mb.rolemenu.entity.RoleMenu;

@MapperScan
public interface RoleMenuDao extends BaseDao<RoleMenu, java.lang.String> {

	/**
	 * 根据角色id查询对应的菜单
	 * 
	 * @param roleId
	 *            角色id
	 * @return
	 */
	public List<RoleMenu> selectRoleMenuListByRoleId(String roleId);

}
