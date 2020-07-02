package com.mb.role.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mb.role.dao.RoleDao;
import com.mb.role.entity.Role;
import com.mb.role.service.IRoleService;
import com.mb.rolemenu.entity.RoleMenu;

@Service
public class RoleServiceImpl implements IRoleService {

	@Resource
	private RoleDao roleDao;

	@Override
	public boolean save(Role role) {
		boolean rtn = false;
		if (null != role) {
			role.setId(UUID.randomUUID().toString().replace("-", ""));
			role.setCreateTime(new Date());
			Integer len = roleDao.save(role);
			if (len > 0) {
				rtn = true;
			}
		}
		return rtn;
	}

	@Override
	public boolean update(Role role) {
		boolean rtn = false;
		if (null != role) {
			role.setUpdateTime(new Date());
			Integer len = roleDao.update(role);
			if (len > 0) {
				rtn = true;
			}
		}
		return rtn;
	}

	@Override
	public boolean deleteById(String id) {
		boolean rtn = false;
		Integer isDel = roleDao.deleteById(id);
		if (isDel > 0) {
			rtn = true;
		}
		return rtn;
	}

	@Override
	public Role findById(String id) {
		return roleDao.findById(id);
	}

	@Override
	public List<Role> getListByParam(Map<String, Object> paramMap) {
		return roleDao.getListByParam(paramMap);
	}

	@Override
	public List<Role> getRolesByUsername(String username) {
		return roleDao.getRolesByUsername(username);
	}

	@Override
	public boolean isHasRolename(Role role) {
		boolean isHas = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Role> allRoleList = roleDao.getListByParam(paramMap);// 全部角色
		List<String> rolenameList = new ArrayList<String>();
		for (int i = 0; i < allRoleList.size(); i++) {
			Role tempRole = allRoleList.get(i);
			String tempId = tempRole.getId();
			if (StringUtils.isNotBlank(role.getId())) {// id不为空，表示是更新
				if (!role.getId().equals(tempId)) {// 去除修改的这条数据
					rolenameList.add(tempRole.getRolename());
				}
			} else {// 新增
				rolenameList.add(tempRole.getRolename());
			}
		}
		if (null != rolenameList && rolenameList.size() > 0) {
			isHas = rolenameList.contains(role.getRolename());
		}
		return isHas;
	}

	@Override
	public void insertRoleMenu(String roleId, List<String> menuList) {
		Map<String, Object> userRoleMap = new HashMap<String, Object>();
		// 删除该角色下的菜单
		roleDao.deleteRoleMenuByRoleId(roleId);
		// 新增该角色下的菜单
		List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();
		RoleMenu roleMenu = null;
		for (String menuId : menuList) {
			roleMenu = new RoleMenu();
			roleMenu.setId(UUID.randomUUID().toString().replace("-", ""));// 生成id
			roleMenu.setRoleId(roleId);
			roleMenu.setMenuId(menuId);
			roleMenuList.add(roleMenu);
		}
		userRoleMap.put("roleMenuList", roleMenuList);
		roleDao.insertRoleMenu(userRoleMap);
	}

}
