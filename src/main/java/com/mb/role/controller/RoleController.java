package com.mb.role.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mb.base.BaseController;
import com.mb.login.entity.User;
import com.mb.role.entity.Role;
import com.mb.role.service.IRoleService;
import com.mb.userrole.entity.UserRole;
import com.mb.userrole.service.IUserRoleService;
import com.mb.util.JsonResult;
import com.mb.util.MbConstant;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<Role> {

	private final static Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IUserRoleService userRoleService;

	@RequestMapping("/gotoList")
	public String gotoList(HttpServletRequest request) {
		LOGGER.info("RoleController goto role list page");
		return "role/roleList";
	}

	@RequestMapping("/gotoRoleMenu")
	public String gotoRoleMenu(Model model, HttpServletRequest request) {
		LOGGER.info("RoleController goto roleMenu list page");
		String roleId = request.getParameter("roleId");
		model.addAttribute("roleId", roleId);// 需要添加角色的id
		return "role/selectMenu";
	}

	@RequestMapping("/gotoUpdatePage")
	public String gotoUpdatePage(Model model, Role role) {
		LOGGER.info("RoleController goto update role page");
		Role tempRole = new Role();
		if (StringUtils.isNotBlank(role.getId())) {// 前台传来的id不为空
			tempRole = roleService.findById(role.getId());
		}
		model.addAttribute("role", tempRole);
		return "role/updateRole";
	}

	@RequestMapping("/gotoAddPage")
	public String gotoAddPage(HttpServletRequest request) {
		LOGGER.info("RoleController goto add role page");
		return "role/addRole";
	}

	@ResponseBody
	@RequestMapping(value = "/save", produces = "text/html;charset=UTF-8")
	public String save(HttpServletRequest request, HttpServletResponse response, Role role) {
		LOGGER.info("RoleController save role");
		JsonResult result = new JsonResult(false, "保存失败");
		boolean isHas = roleService.isHasRolename(role);// 判断角色名称是否存在
		if (!isHas) {
			User userInfo = (User) request.getSession().getAttribute(MbConstant.USERINFO);
			role.setCreateUser(userInfo.getUsername());
			boolean rtn = roleService.save(role);
			if (rtn) {
				result.setSuccess(true);
				result.setMsg("保存成功");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("角色名称已存在，请修改。");
		}
		return JSONObject.fromObject(result).toString();
	}

	@ResponseBody
	@RequestMapping(value = "/update", produces = "text/html;charset=UTF-8")
	public String update(HttpServletRequest request, HttpServletResponse response, Role role) {
		LOGGER.info("RoleController update user");
		JsonResult result = new JsonResult(false, "更新失败");
		boolean isHas = roleService.isHasRolename(role);// 判断角色名称是否存在
		if (!isHas) {
			boolean rtn = roleService.update(role);
			if (rtn) {
				result.setSuccess(true);
				result.setMsg("修改成功");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("用户名已存在，请修改。");
		}
		return JSONObject.fromObject(result).toString();
	}

	@ResponseBody
	@RequestMapping("/deleteById")
	public JsonResult deleteById(HttpServletRequest request, HttpServletResponse response, Role role) {
		LOGGER.info("RoleController delete role");
		JsonResult result = new JsonResult(false, "删除失败");
		boolean rtn = roleService.deleteById(role.getId());
		if (rtn) {
			result.setSuccess(true);
			result.setMsg("删除成功");
		}
		return result;
	}

	// 设置分页数据
	@Override
	public List<Role> setPageList(HttpServletRequest request, HttpServletResponse response, Role Role) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("rolename", Role.getRolename());// 模糊查询
		List<Role> pageList = roleService.getListByParam(paramMap);
		return pageList;
	}

	/**
	 * 不分页
	 * 
	 * @param request
	 * @param response
	 * @param t
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/notPageList4Role")
	public Map<String, Object> notPageList(HttpServletRequest request, HttpServletResponse response, Role role) {
		List<Role> returnPageList = new ArrayList<Role>();
		List<Role> pageList = this.setPageList(request, response, role);// 不分页数据
		String userId = request.getParameter("userId");
		List<UserRole> userRoleList = userRoleService.selectUserRoleListByUserId(userId);// 根据userid查询用户角色的id
		List<String> roleIdList = new ArrayList<String>();
		for (UserRole userRole : userRoleList) {
			roleIdList.add(userRole.getRoleId());// 需要选中的角色id的集合
		}
		Role tempRole = null;
		for (int i = 0; i < pageList.size(); i++) {
			tempRole = pageList.get(i);
			if (roleIdList.contains(tempRole.getId())) {
				tempRole.setIsCheck("1");// 选中
			} else {
				tempRole.setIsCheck("0");// 未选中
			}
			returnPageList.add(tempRole);
		}
		Integer total = returnPageList.size();
		Map<String, Object> pageMap = new HashMap<String, Object>();
		pageMap.put("total", total);
		pageMap.put("rows", returnPageList);
		return pageMap;
	}

	@ResponseBody
	@RequestMapping(value = "/chooseMenu4Role", method = RequestMethod.POST)
	public JsonResult chooseMenu4Role(HttpServletRequest request) {
		JsonResult result = new JsonResult(false, "菜单选择失败");
		try {
			String roleId = request.getParameter("roleId") == null ? "" : request.getParameter("roleId");// 需要添加菜单角色的id
			String menuIdsStr = request.getParameter("menuIdsStr") == null ? "" : request.getParameter("menuIdsStr");// 菜单id的字符串
			if (StringUtils.isNotBlank(roleId) && StringUtils.isNotBlank(menuIdsStr)) {
				String[] menuIds = menuIdsStr.split(",");// 分割字符串，逗号
				List<String> menuIdList = Arrays.asList(menuIds);// 数组转list
				roleService.insertRoleMenu(roleId, menuIdList);
				result.setMsg("菜单选择成功");
				result.setSuccess(true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("菜单选择异常");
			result.setSuccess(false);
		}
		return result;
	}

}