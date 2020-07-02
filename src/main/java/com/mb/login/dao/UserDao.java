package com.mb.login.dao;

import java.util.Map;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.base.BaseDao;
import com.mb.login.entity.User;

@MapperScan
public interface UserDao extends BaseDao<User, java.lang.String> {

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 *            用户名
	 * @return
	 */
	public User getUserByName(String username);

	/**
	 * 根据用户ID插入对应的角色
	 * 
	 * @param userRoleMap
	 */
	public void insertUserRole(Map<String, Object> userRoleMap);

	/**
	 * 根据用户userId删除用户角色对应关系
	 * 
	 * @param userId
	 */
	public void deleteUserRoleByUserId(String userId);

}
