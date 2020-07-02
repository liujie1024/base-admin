package com.mb.menu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 菜单分类，分为一级菜单和二级菜单
 */
public class MenuClassify implements Serializable {

	private static final long serialVersionUID = -8343102279874789470L;

	private Menu oneMenu;// 一级菜单

	private List<Menu> twoMenuList;// 二级菜单集合

	public Menu getOneMenu() {
		return oneMenu;
	}

	public void setOneMenu(Menu oneMenu) {
		this.oneMenu = oneMenu;
	}

	public List<Menu> getTwoMenuList() {
		return twoMenuList;
	}

	public void setTwoMenuList(List<Menu> twoMenuList) {
		this.twoMenuList = twoMenuList;
	}

}