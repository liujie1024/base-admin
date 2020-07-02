package com.mb.userrole.dao;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.mb.base.BaseDao;
import com.mb.userrole.entity.UserRole;

@MapperScan
public interface UserRoleDao extends BaseDao<UserRole, java.lang.String> {

	/**
	 * 根据用户id查询对应角色
	 * 
	 * @param id
	 *            用户的id
	 * @return
	 */
	public List<UserRole> selectUserRoleListByUserId(String userId);

}