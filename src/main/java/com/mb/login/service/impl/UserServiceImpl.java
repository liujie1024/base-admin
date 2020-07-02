package com.mb.login.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.mb.login.dao.UserDao;
import com.mb.login.entity.User;
import com.mb.login.service.IUserService;
import com.mb.role.entity.Role;
import com.mb.userrole.entity.UserRole;
import com.mb.util.MD5Util;

@Service
public class UserServiceImpl implements IUserService {

	@Resource
	private UserDao userDao;

	@Override
	public boolean save(User user) {
		boolean rtn = false;
		if (null != user) {
			user.setId(UUID.randomUUID().toString().replace("-", ""));
			user.setPassword(MD5Util.md5(user.getPassword()));// MD5����
			user.setCreateTime(new Date());
			user.setState(0);
			Integer len = userDao.save(user);
			if (len > 0) {
				rtn = true;
			}
		}
		return rtn;
	}

	@Override
	public boolean update(User user) {
		boolean rtn = false;
		if (null != user) {
			user.setUpdateTime(new Date());
			Integer len = userDao.update(user);
			if (len > 0) {
				rtn = true;
			}
		}
		return rtn;
	}

	@Override
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}

	@Override
	public User getUserByParam(Map<String, Object> paramMap) {
		User userInfo = null;
		List<User> userList = userDao.getListByParam(paramMap);
		if (null != userList && userList.size() > 0) {
			userInfo = userList.get(0);
		}
		return userInfo;
	}

	@Override
	public boolean isHasUserByName(User user) {
		boolean isHas = false;
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<User> allUserList = userDao.getListByParam(paramMap);
		List<String> usernameList = new ArrayList<String>();
		for (int i = 0; i < allUserList.size(); i++) {
			User tempUser = allUserList.get(i);
			String tempId = tempUser.getId();
			if (StringUtils.isNotBlank(user.getId())) {
				if (!user.getId().equals(tempId)) {
					usernameList.add(tempUser.getUsername());
				}
			} else {
				usernameList.add(tempUser.getUsername());
			}
		}
		if (null != usernameList && usernameList.size() > 0) {
			isHas = usernameList.contains(user.getUsername());
		}
		return isHas;
	}

	@Override
	public boolean deleteById(String id) {
		boolean rtn = false;
		Integer isDel = userDao.deleteById(id);
		if (isDel > 0) {
			rtn = true;
		}
		return rtn;
	}

	@Override
	public User findById(String id) {
		return userDao.findById(id);
	}

	@Override
	public List<User> getListByParam(Map<String, Object> paramMap) {
		return userDao.getListByParam(paramMap);
	}

	@Override
	public void insertUserRole(String userId, List<Role> roleList) {
		Map<String, Object> userRoleMap = new HashMap<String, Object>();
		userDao.deleteUserRoleByUserId(userId);
		List<UserRole> userRoleList = new ArrayList<UserRole>();
		UserRole userRole = null;
		for (Role tempRole : roleList) {
			userRole = new UserRole();
			userRole.setId(UUID.randomUUID().toString().replace("-", ""));// ���id
			userRole.setRoleId(tempRole.getId());
			userRole.setUserId(userId);
			userRoleList.add(userRole);
		}
		userRoleMap.put("userRoleList", userRoleList);
		userDao.insertUserRole(userRoleMap);
	}

}
