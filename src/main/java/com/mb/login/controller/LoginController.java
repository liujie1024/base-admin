package com.mb.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mb.login.entity.User;
import com.mb.login.service.IUserService;
import com.mb.menu.entity.Menu;
import com.mb.menu.entity.MenuClassify;
import com.mb.menu.service.IMenuService;
import com.mb.role.entity.Role;
import com.mb.util.JsonResult;
import com.mb.util.MD5Util;
import com.mb.util.MbConstant;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IMenuService menuService;

	@ResponseBody
	@RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		boolean success = false;
		String msg = "用户名或者密码错误";
		String username = request.getParameter("username");// 用户名
		String password = request.getParameter("password");// 密码
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);
		paramMap.put("password", MD5Util.md5(password));
		User userInfo = userService.getUserByParam(paramMap);
		if (null != userInfo) {
			request.getSession().setAttribute(MbConstant.USERINFO, userInfo);// 用户信息保存在session中
			success = true;
			msg = "登录成功";
		}
		jsonResult.setSuccess(success);
		jsonResult.setMsg(msg);
		return JSONObject.fromObject(jsonResult).toString();
	}

	@RequestMapping("/main")
	public String main(HttpServletRequest request, HttpServletResponse response) {
		User userInfo = (User) request.getSession().getAttribute(MbConstant.USERINFO);// 登录用户的基本信息
		List<MenuClassify> menuClassifyList = this.setMenuListByUser(userInfo);
		request.getSession().setAttribute("menuClassifyList", menuClassifyList);// 该用户下的菜单
		return "/main";
	}

	/**
	 * 根据当前用户查询菜单信息
	 * 
	 * @param userInfo
	 *            用户信息
	 * @return
	 */
	private List<MenuClassify> setMenuListByUser(User userInfo) {
		// 1、查询该用户下的所有菜单
		List<Menu> userAllMenuList = new ArrayList<Menu>();
		if (null != userInfo) {// 用户不为空，查询出来角色和菜单
			List<Role> roleList = userInfo.getRoleList();// 该用户的角色
			if (null != roleList && roleList.size() > 0) {
				for (Role role : roleList) {
					List<Menu> tempUserMenuList = menuService.getMenuListByRoleId(role.getId());// 该用户角色下的菜单
					if (null != tempUserMenuList && tempUserMenuList.size() > 0) {
						userAllMenuList.addAll(tempUserMenuList);
					}
				}
			}
		}
		// 2、找到所有的一级菜单
		List<Menu> oneMenuList = new ArrayList<Menu>();
		for (int i = 0; i < userAllMenuList.size(); i++) {
			if (StringUtils.isBlank(userAllMenuList.get(i).getParentId())) { // 一级菜单没有parentId
				oneMenuList.add(userAllMenuList.get(i));
			}
		}
		// 3、查询一级菜单下的子菜单集合
		List<MenuClassify> menuClassifyList = new ArrayList<MenuClassify>();
		MenuClassify menuClassify = null;
		for (Menu menu : oneMenuList) {
			menuClassify = new MenuClassify();
			menuClassify.setOneMenu(menu);
			menuClassify.setTwoMenuList(this.getChildMenuList(menu.getId(), userAllMenuList));// 子菜单集合
			menuClassifyList.add(menuClassify);
		}
		return menuClassifyList;
	}

	/**
	 * 查询该用户下的一级菜单下的子菜单的集合
	 * 
	 * @param id
	 *            父菜单的id
	 * @param userAllMenuList
	 *            该用户下的所有菜单
	 * @return
	 */
	private List<Menu> getChildMenuList(String id, List<Menu> userAllMenuList) {
		List<Menu> childList = new ArrayList<>();
		for (Menu menu : userAllMenuList) {
			if (StringUtils.isNotBlank(menu.getParentId())) {// 遍历所有节点，将父菜单id与传过来的id比较
				if (menu.getParentId().equals(id)) {
					childList.add(menu);
				}
			}
		}
		return childList;
	}

	@ResponseBody
	@RequestMapping("/logout")
	public JsonResult logout(HttpServletRequest request, HttpServletResponse response) {
		JsonResult jsonResult = new JsonResult();
		request.getSession().removeAttribute(MbConstant.USERINFO);// 去除session
		request.getSession().invalidate();
		jsonResult.setSuccess(true);
		jsonResult.setMsg("退出成功");
		return jsonResult;
	}

	@RequestMapping("/welcome")
	public String welcome(HttpServletRequest request, HttpServletResponse response) {
		return "/welcome";
	}

	@RequestMapping("/sessionTime")
	public String sessionTime(HttpServletRequest request, HttpServletResponse response) {
		return "/sessionTime";
	}

	public static void main(String[] args) {
		System.out.println(MD5Util.md5("123456"));
	}

}
