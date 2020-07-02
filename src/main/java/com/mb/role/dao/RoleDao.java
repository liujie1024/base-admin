package com.mb.role.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.base.BaseDao;
import com.mb.role.entity.Role;

@MapperScan
public interface RoleDao extends BaseDao<Role, java.lang.String> {

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
	 * 获得所有的角色
	 * 
	 * @return
	 */
	public List<Role> getAllRole();

	/**
	 * 根据用户id查询对应角色
	 * 
	 * @param id
	 *            用户的id
	 * @return
	 */
	public List<Role> selectRoleListByUserId(String id);

	/**
	 * 根据角色插入对应的菜单
	 * 
	 * @param roleMenuMap
	 */
	public void insertRoleMenu(Map<String, Object> roleMenuMap);

	/**
	 * 根据角色id删除对应的角色菜单的对应关系
	 * 
	 * @param roleId
	 *            角色id
	 */
	public void deleteRoleMenuByRoleId(String roleId);

}
