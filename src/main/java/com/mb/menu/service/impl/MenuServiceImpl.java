package com.mb.menu.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mb.menu.dao.MenuDao;
import com.mb.menu.entity.Menu;
import com.mb.menu.service.IMenuService;

@Service
public class MenuServiceImpl implements IMenuService {

	@Resource
	private MenuDao menuDao;

	@Override
	public boolean save(Menu menu) {
		boolean rtn = false;
		if (null != menu) {
			menu.setId(UUID.randomUUID().toString().replace("-", ""));
			menu.setCreateTime(new Date());
			Integer len = menuDao.save(menu);
			if (len > 0) {
				rtn = true;
			}
		}
		return rtn;
	}

	@Override
	public boolean update(Menu menu) {
		boolean rtn = false;
		if (null != menu) {
			menu.setUpdateTime(new Date());
			Integer len = menuDao.update(menu);
			if (len > 0) {
				rtn = true;
			}
		}
		return rtn;
	}

	@Override
	public boolean deleteById(String id) {
		boolean rtn = false;
		Integer isDel = menuDao.deleteById(id);
		if (isDel > 0) {
			rtn = true;
		}
		return rtn;
	}

	@Override
	public Menu findById(String id) {
		return null;
	}

	@Override
	public List<Menu> getListByParam(Map<String, Object> paramMap) {
		return null;
	}

	@Override
	public List<Menu> getMenuListByRoleId(String roleId) {
		return menuDao.getMenuListByRoleId(roleId);
	}

	@Override
	public List<Menu> getMenuListByParentId(String parentId) {
		return menuDao.getMenuListByParentId(parentId);
	}

	@Override
	public List<Menu> getAllMenuList(Map<String, Object> paramMap) {
		return menuDao.getAllMenuList(paramMap);
	}

	@Override
	public List<Menu> getParentMenuList(Map<String, Object> paramMap) {
		return menuDao.getParentMenuList(paramMap);
	}

	@Override
	public boolean isHasMenuByName(Menu menu) {
		boolean isHas = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Menu> allMenuList = menuDao.getListByParam(paramMap);
		List<String> menunameList = new ArrayList<String>();
		for (int i = 0; i < allMenuList.size(); i++) {
			Menu tempMenu = allMenuList.get(i);
			String tempId = tempMenu.getId();
			if (StringUtils.isNotBlank(menu.getId())) {
				if (!menu.getId().equals(tempId)) {
					menunameList.add(tempMenu.getMenunama());
				}
			} else {
				menunameList.add(tempMenu.getMenunama());
			}
		}
		if (null != menunameList && menunameList.size() > 0) {
			isHas = menunameList.contains(menu.getMenunama());
		}
		return isHas;
	}

}
