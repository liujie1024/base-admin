package com.mb.userrole.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mb.userrole.dao.UserRoleDao;
import com.mb.userrole.entity.UserRole;
import com.mb.userrole.service.IUserRoleService;

@Service
public class UserRoleServiceImpl implements IUserRoleService {

	@Resource
	private UserRoleDao userroleDao;

	@Override
	public boolean save(UserRole entity) {
		return false;
	}

	@Override
	public boolean update(UserRole entity) {
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public UserRole findById(String id) {
		return null;
	}

	@Override
	public List<UserRole> getListByParam(Map<String, Object> paramMap) {
		return null;
	}

	@Override
	public List<UserRole> selectUserRoleListByUserId(String userId) {
		return userroleDao.selectUserRoleListByUserId(userId);
	}

}
