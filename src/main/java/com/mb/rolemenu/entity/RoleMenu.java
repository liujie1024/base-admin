package com.mb.rolemenu.entity;

import java.io.Serializable;

/**
 * 角色菜单表
 */
public class RoleMenu implements Serializable {

	private static final long serialVersionUID = 3721531087821431088L;

	private String id;// 主键

	private String roleId;// 角色id

	private String menuId;// 菜单id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

}
