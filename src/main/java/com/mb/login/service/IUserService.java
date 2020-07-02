package com.mb.login.service;

import java.util.List;
import java.util.Map;

import com.mb.base.BaseService;
import com.mb.login.entity.User;
import com.mb.role.entity.Role;

public interface IUserService extends BaseService<User, java.lang.String> {

	/**
	 * 根据用户名查询用户
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByName(String username);

	/**
	 * 根据参数查询用户
	 * 
	 * @param paramMap
	 * @return
	 */
	public User getUserByParam(Map<String, Object> paramMap);

	/**
	 * 该用户名在数据库中是否存在
	 * 
	 * @param user
	 *            用户
	 * @return
	 */
	public boolean isHasUserByName(User user);

	/**
	 * 根据用户ID插入对应的角色
	 * 
	 * @param user
	 *            当前用户
	 * @param roleList
	 *            角色list
	 */
	public void insertUserRole(String userId, List<Role> roleList);

}
