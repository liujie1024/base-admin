package com.mb.role.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mb.menu.entity.Menu;

/**
 * 角色表
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1904614193690312605L;

	private String id;// 主键

	private String rolename;// 角色名称

	private Date createTime;// 创建时间

	private Date updateTime;// 修改时间

	private String createUser;// 创建人

	private List<Menu> menuList;// 角色下的菜单

	private String isCheck;// 是否选中

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public List<Menu> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

}
