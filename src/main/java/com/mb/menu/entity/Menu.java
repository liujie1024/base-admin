package com.mb.menu.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 菜单表
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = -7634357823414992198L;

	private String id;// 主键

	private String menunama;// 菜单名

	private String menuurl;// 菜单url

	private String parentId;// 上一级菜单

	private Date createTime;// 创建时间

	private Date updateTime;// 修改时间

	private String createUser;// 创建人

	private String grade;// 级别，1：一级菜单，2：二级菜单

	private String auth;// 权限区分，page：页面，auth：功能按钮

	private String extend1;// 扩展字段1

	private String extend2;// 扩展字段2

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMenunama() {
		return menunama;
	}

	public void setMenunama(String menunama) {
		this.menunama = menunama;
	}

	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getExtend1() {
		return extend1;
	}

	public void setExtend1(String extend1) {
		this.extend1 = extend1;
	}

	public String getExtend2() {
		return extend2;
	}

	public void setExtend2(String extend2) {
		this.extend2 = extend2;
	}

}
