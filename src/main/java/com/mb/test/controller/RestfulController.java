package com.mb.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mb.login.entity.User;
import com.mb.login.service.IUserService;
import com.mb.test.entity.ResultBody;

@Controller
@RequestMapping("/api/user")
public class RestfulController {

	@Autowired
	private IUserService userService;

	@ResponseBody
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User getUser(@PathVariable String id) {
		System.out.println("id：" + id);
		User user = userService.findById(id);
		return user;
	}
	
	@ResponseBody
	@RequestMapping(value = "/{pageSize}/{pageNumber}", method = RequestMethod.POST)
	public ResultBody getUserListByParam(@PathVariable int pageSize,@PathVariable int pageNumber) {
		System.out.println("一页多少条pageSize：" + pageSize+",第几页pageNumber：" + pageNumber);
		Page<User> page = PageHelper.startPage(pageNumber, pageSize, true); // 核心分页代码
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<User> userList = userService.getListByParam(paramMap);
		ResultBody rb = new ResultBody();
		rb.setSuccess(true);
		rb.setMessage("查询成功");
		rb.setObjList(userList);
		rb.setTotal(new Long(page.getTotal()).intValue());
		return rb;
	}

}