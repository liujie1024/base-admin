package com.mb.test.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mb.redis.service.impl.RedisCacheUtil;

@Controller
@RequestMapping("/redisTest")
public class RedisTest {

//	@Autowired
//	private RedisCacheUtil<Object> redisCache;
//
//	@ResponseBody
//	@RequestMapping("/testSetCache")
//	public List<String> testSetCache(HttpServletRequest req, HttpServletResponse rsp) {
//		List<String> testList = new ArrayList<>();
//		for (int i = 0; i < 20; i++) {
//			testList.add("test" + i);
//		}
//		redisCache.setCacheObject("testList", testList);
//		return testList;
//	}
//
//	@ResponseBody
//	@RequestMapping("/testGetCache")
//	public List<String> testGetCache(HttpServletRequest req, HttpServletResponse rsp) {
//		List<String> testList = redisCache.getCacheObject("testList");
//		for (int i = 0; i < testList.size(); i++) {
//			System.out.println("redis:" + testList.get(i).toString() + "------");
//		}
//		return testList;
//	}

}
