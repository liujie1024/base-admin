package com.mb.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mb.base.BaseController;
import com.mb.login.entity.User;
import com.mb.menu.entity.BaseCombo;
import com.mb.menu.entity.BaseTree;
import com.mb.menu.entity.Menu;
import com.mb.menu.service.IMenuService;
import com.mb.rolemenu.entity.RoleMenu;
import com.mb.rolemenu.service.IRoleMenuService;
import com.mb.util.JsonResult;
import com.mb.util.MbConstant;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu> {

	private final static Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private IMenuService menuService;

	@Autowired
	private IRoleMenuService roleMenuService;

	@RequestMapping("/gotoList")
	public String gotoList(HttpServletRequest request) {
		LOGGER.info("MenuController goto menuList page");
		return "menu/menuList";
	}

	@ResponseBody
	@RequestMapping(value = "/save", produces = "text/html;charset=UTF-8")
	public String save(HttpServletRequest request, HttpServletResponse response, Menu menu) {
		LOGGER.info("MenuController save menu");
		JsonResult result = new JsonResult(false, "保存失败");
		boolean isHas = menuService.isHasMenuByName(menu);// 判断菜单名是否存在
		if (!isHas) {
			User userInfo = (User) request.getSession().getAttribute(MbConstant.USERINFO);
			menu.setCreateUser(userInfo.getUsername());
			boolean rtn = menuService.save(menu);
			if (rtn) {
				result.setSuccess(true);
				result.setMsg("保存成功。");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("菜单名已存在，请修改。");
		}
		return JSONObject.fromObject(result).toString();
	}

	@ResponseBody
	@RequestMapping(value = "/update", produces = "text/html;charset=UTF-8")
	public String update(HttpServletRequest request, HttpServletResponse response, Menu menu) {
		LOGGER.info("UserController update menu");
		JsonResult result = new JsonResult(false, "更新失败");
		boolean isHas = menuService.isHasMenuByName(menu);// 判断用户名是否存在
		if (!isHas) {
			boolean rtn = menuService.update(menu);
			if (rtn) {
				result.setSuccess(true);
				result.setMsg("修改成功。");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("菜单名已存在，请修改。");
		}
		return JSONObject.fromObject(result).toString();
	}

	@ResponseBody
	@RequestMapping("/deleteById")
	public JsonResult deleteById(HttpServletRequest request, HttpServletResponse response, Menu menu) {
		LOGGER.info("UserController delete user");
		JsonResult result = new JsonResult(false, "删除失败");
		String parentId = menu.getId();// 菜单的id
		List<Menu> childrenList = menuService.getMenuListByParentId(parentId);
		if (null != childrenList && childrenList.size() > 0) {
			result.setSuccess(false);
			result.setMsg("请先删除该菜单的子菜单");
		} else {
			boolean rtn = menuService.deleteById(parentId);
			if (rtn) {
				result.setSuccess(true);
				result.setMsg("删除成功");
			}
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/initMenuTree", method = RequestMethod.POST)
	public List<BaseTree> initMenuTree(HttpServletRequest request) {
		String roleId = request.getParameter("roleId") == null ? "" : request.getParameter("roleId");
		List<String> menuIdList = new ArrayList<String>();// 角色下的菜单集合
		if (StringUtils.isNotBlank(roleId)) {
			List<RoleMenu> roleMenuList = roleMenuService.selectRoleMenuListByRoleId(roleId);// 该角色下的菜单
			for (RoleMenu roleMenu : roleMenuList) {
				menuIdList.add(roleMenu.getMenuId());
			}
		}
		// -----获取父菜单和子菜单的集合--------------------------------------------
		List<BaseTree> rtnMenuTreeList = new ArrayList<BaseTree>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Menu> parentMenuList = menuService.getParentMenuList(paramMap);// 父菜单集合
		BaseTree tree = null;
		for (Menu parentMenu : parentMenuList) {
			tree = new BaseTree();
			tree.setId(parentMenu.getId());
			tree.setText(parentMenu.getMenunama());
			tree.setAttributes(parentMenu);// 菜单的所有属性
			// 获取父菜单下的子菜单
			List<Menu> childrenMenuList = menuService.getMenuListByParentId(parentMenu.getId());// 子菜集合
			List<BaseTree> childrenList = this.resetMenuListt(childrenMenuList, menuIdList);
			tree.setChildren(childrenList);
			rtnMenuTreeList.add(tree);
		}
		return rtnMenuTreeList;
	}

	/**
	 * 菜单重置，设置为tree的结构
	 * 
	 * @param menuList
	 * @return
	 */
	private List<BaseTree> resetMenuListt(List<Menu> menuList, List<String> menuIdList) {
		List<BaseTree> rtnMenuList = new ArrayList<BaseTree>();
		if (null != menuList && menuList.size() > 0) {
			BaseTree tree = null;
			for (Menu menu : menuList) {
				tree = new BaseTree();
				tree.setId(menu.getId());
				tree.setText(menu.getMenunama());
				if (menuIdList.contains(menu.getId())) {// 选中
					tree.setChecked(true);
				}
				tree.setAttributes(menu);// 菜单的所有属性
				rtnMenuList.add(tree);
			}
		}
		return rtnMenuList;
	}

	@ResponseBody
	@RequestMapping(value = "/initParentMenu", method = RequestMethod.POST)
	public List<BaseCombo> initParentMenu(HttpServletRequest request) {
		List<BaseCombo> rtnMenuTreeList = new ArrayList<BaseCombo>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Menu> parentMenuList = menuService.getParentMenuList(paramMap);// 父菜单集合
		BaseCombo combo = null;
		for (Menu parentMenu : parentMenuList) {
			combo = new BaseCombo();
			combo.setId(parentMenu.getId());
			combo.setText(parentMenu.getMenunama());
			rtnMenuTreeList.add(combo);
		}
		return rtnMenuTreeList;
	}

	@Override
	public List<Menu> setPageList(HttpServletRequest request, HttpServletResponse response, Menu menu) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Menu> menuList = menuService.getAllMenuList(paramMap);
		return menuList;
	}

}