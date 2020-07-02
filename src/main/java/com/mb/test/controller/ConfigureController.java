package com.mb.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mb.db.DataSourceContextHolder;
import com.mb.test.entity.Configure;
import com.mb.test.service.IConfigureService;
import com.mb.util.MbConstant;

@Controller
@RequestMapping("/configure")
public class ConfigureController {

	private final static Logger LOGGER = LoggerFactory.getLogger(ConfigureController.class);

	@Autowired
	private IConfigureService configureService;

	@RequestMapping("/gotoList")
	public String gotoList(HttpServletRequest request) {
		LOGGER.info("UserController goto userList page");

		// 注意这里在调用service前切换到bi的数据源
		DataSourceContextHolder.setDbType(MbConstant.DS_BI);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Configure> configureList = configureService.selectByParamListPage(paramMap);
		System.out.println("--------------" + configureList.size());

		return "user/userList";
	}

}
