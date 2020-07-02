package com.mb.login.controller;

import java.util.Date;
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
import com.mb.login.service.IUserService;
import com.mb.role.entity.Role;
import com.mb.util.JsonResult;
import com.mb.util.MD5Util;
import com.mb.util.MbConstant;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User> {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;

	@RequestMapping("/gotoList")
	public String gotoList(HttpServletRequest request) {
		LOGGER.info("UserController goto userList page");
		return "user/userList";
	}

	@RequestMapping("/gotoUpdatePage")
	public String gotoUpdatePage(Model model, User user) {
		LOGGER.info("UserController goto update user page");
		User tempUser = new User();
		if (StringUtils.isNotBlank(user.getId())) {// 前台传来的id不为空
			tempUser = userService.findById(user.getId());
		}
		model.addAttribute("user", tempUser);
		return "user/updateUser";
	}

	@RequestMapping("/gotoAddPage")
	public String gotoAddPage(HttpServletRequest request) {
		LOGGER.info("UserController goto add user page");
		return "user/addUser";
	}

	@RequestMapping("/gotoPswPage")
	public String gotoPswPage(HttpServletRequest request) {
		LOGGER.info("UserController goto updatePsw page");
		return "user/updatePsw";
	}

	@ResponseBody
	@RequestMapping(value = "/save", produces = "text/html;charset=UTF-8")
	public String save(HttpServletRequest request, HttpServletResponse response, User user) {
		LOGGER.info("UserController save user");
		JsonResult result = new JsonResult(false, "保存失败");
		boolean isHas = userService.isHasUserByName(user);// 判断用户名是否存在
		if (!isHas) {
			User userInfo = (User) request.getSession().getAttribute(MbConstant.USERINFO);
			user.setCreateUser(userInfo.getUsername());
			boolean rtn = userService.save(user);
			if (rtn) {
				result.setSuccess(true);
				result.setMsg("保存成功。");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("用户名已存在，请修改。");
		}
		return JSONObject.fromObject(result).toString();
	}

	@ResponseBody
	@RequestMapping(value = "/update", produces = "text/html;charset=UTF-8")
	public String update(HttpServletRequest request, HttpServletResponse response, User user) {
		LOGGER.info("UserController update user");
		JsonResult result = new JsonResult(false, "更新失败");
		boolean isHas = userService.isHasUserByName(user);// 判断用户名是否存在
		if (!isHas) {
			boolean rtn = userService.update(user);
			if (rtn) {
				result.setSuccess(true);
				result.setMsg("修改成功。");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("用户名已存在，请修改。");
		}
		return JSONObject.fromObject(result).toString();
	}

	@ResponseBody
	@RequestMapping("/deleteById")
	public JsonResult deleteById(HttpServletRequest request, HttpServletResponse response, User user) {
		LOGGER.info("UserController delete user");
		JsonResult result = new JsonResult(false, "删除失败");
		boolean rtn = userService.deleteById(user.getId());
		if (rtn) {
			result.setSuccess(true);
			result.setMsg("删除成功");
		}
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/updatePsw", produces = "text/html;charset=UTF-8")
	public String updatePsw(HttpServletRequest request, HttpServletResponse response) {
		LOGGER.info("UserController update user");
		JsonResult result = new JsonResult(false, "修改失败");
		User user = (User) request.getSession().getAttribute(MbConstant.USERINFO);
		String oldPsw = request.getParameter("oldPsw") == null ? "" : MD5Util.md5(request.getParameter("oldPsw"));// 旧密码
		String newPsw = request.getParameter("newPsw") == null ? "" : MD5Util.md5(request.getParameter("newPsw"));// 新密码
		String confireNewPsw = request.getParameter("confireNewPsw") == null ? "" : MD5Util.md5(request.getParameter("confireNewPsw"));// 确认新密码
		if (newPsw.equals(confireNewPsw)) {
			if (oldPsw.equals(user.getPassword())) {// 和以前的密码比对
				user.setPassword(newPsw);
				user.setUpdateTime(new Date());
				boolean isSuccess = userService.update(user);// 更新数据
				if (isSuccess) {
					result.setSuccess(true);
					result.setMsg("修改成功，请重新登录");
				} else {
					result.setSuccess(false);
					result.setMsg("密码修改异常");
				}
			} else {
				result.setSuccess(false);
				result.setMsg("旧密码不正确");
			}
		} else {
			result.setSuccess(false);
			result.setMsg("2次密码不一致");
		}
		return JSONObject.fromObject(result).toString();
	}

	// 设置分页数据
	@Override
	public List<User> setPageList(HttpServletRequest request, HttpServletResponse response, User user) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("usernameSearch", user.getUsernameSearch());// 用户名模糊查询
		List<User> userList = userService.getListByParam(paramMap);
		return userList;
	}

	@RequestMapping("/gotoSelectRolePage")
	public String gotoSelectRolePage(Model model, HttpServletRequest request) {
		LOGGER.info("UserController goto selectRole page");
		String userId = request.getParameter("userId");
		model.addAttribute("userId", userId);// 需要添加角色用户的id
		return "user/selectRole";
	}

	@SuppressWarnings({ "unchecked", "static-access" })
	@ResponseBody
	@RequestMapping(value = "/chooseRole4User", method = RequestMethod.POST)
	public JsonResult chooseRole4User(HttpServletRequest request) {
		JsonResult result = new JsonResult(false, "修改失败");
		try {
			String userId = request.getParameter("userId");// 需要添加角色用户的id
			String roleListStr = request.getParameter("roleListStr");// json格式的角色
			JSONArray data = JSONArray.fromObject(roleListStr);
			List<Role> Rolelist = (List<Role>) data.toCollection(data, Role.class);// jsonarray转换成list
			userService.insertUserRole(userId, Rolelist);
			result.setMsg("修改成功");
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMsg("修改异常");
			result.setSuccess(false);
		}
		return result;
	}

}