package com.mb.rolemenu.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mb.rolemenu.dao.RoleMenuDao;
import com.mb.rolemenu.entity.RoleMenu;
import com.mb.rolemenu.service.IRoleMenuService;

@Service
public class RoleMenuServiceImpl implements IRoleMenuService {

	@Resource
	private RoleMenuDao roleMenuDao;

	@Override
	public boolean save(RoleMenu entity) {
		return false;
	}

	@Override
	public boolean update(RoleMenu entity) {
		return false;
	}

	@Override
	public boolean deleteById(String id) {
		return false;
	}

	@Override
	public RoleMenu findById(String id) {
		return null;
	}

	@Override
	public List<RoleMenu> getListByParam(Map<String, Object> paramMap) {
		return null;
	}

	@Override
	public List<RoleMenu> selectRoleMenuListByRoleId(String roleId) {
		return roleMenuDao.selectRoleMenuListByRoleId(roleId);
	}

}
