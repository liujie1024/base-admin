package com.mb.login.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.mb.role.entity.Role;

/**
 * 用户表
 */
public class User implements Serializable {

	private static final long serialVersionUID = -8853433036407719788L;

	private String id;// 主键

	private String username;// 用户名

	private String password;// 密码

	private Integer state;// 状态

	private Date createTime;// 创建时间

	private Date updateTime;// 修改时间

	private String createUser;// 创建人

	private List<Role> roleList;// 用户下的角色

	private String usernameSearch;// 用户名模糊查询

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getUsernameSearch() {
		return usernameSearch;
	}

	public void setUsernameSearch(String usernameSearch) {
		this.usernameSearch = usernameSearch;
	}

}