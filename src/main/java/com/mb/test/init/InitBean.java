package com.mb.test.init;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitBean implements InitializingBean {

	@Autowired
	private DataMappingCache cache;

	private static final Logger LOGGER = Logger.getLogger(InitBean.class);

	private static boolean FIRST_INIT = false;// 防止初始化的时候执行2次

	@Override
	public void afterPropertiesSet() throws Exception {
		if (FIRST_INIT) {
			LOGGER.info("----->>>>>hello world......");
			Map<String, Object> objMap = cache.getCache();
			LOGGER.info("----->>>>>hello world......" + cache.getObjByKey("key--1"));
		} else {
			FIRST_INIT = true;
		}
	}

	public void test() {
		Map<String, Object> objMap = cache.getCache();
		LOGGER.info("----->>>>>hello world......" + objMap.size());
	}

	public static void main(String[] args) {
		InitBean init = new InitBean();
		init.test();
	}

}
