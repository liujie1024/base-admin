package com.mb.userrole.entity;

import java.io.Serializable;

/**
 * 用户角色表
 */
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1606674276585081584L;

	private String id;// 主键

	private String userId;// 用户id

	private String roleId;// 角色id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}