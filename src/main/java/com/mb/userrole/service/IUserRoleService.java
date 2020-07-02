package com.mb.userrole.service;

import java.util.List;

import com.mb.base.BaseService;
import com.mb.userrole.entity.UserRole;

public interface IUserRoleService extends BaseService<UserRole, java.lang.String> {

	/**
	 * 根据用户id查询对应角色
	 * 
	 * @param id
	 *            用户的id
	 * @return
	 */
	public List<UserRole> selectUserRoleListByUserId(String userId);

}