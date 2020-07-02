package com.mb.listener;

import com.mb.baseType.service.IBaseTypeGroupService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * 系统初始化监听器,在系统启动时运行,进行一些初始化工作
 */
public class InitListener implements ServletContextListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(InitListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info(">>>>>数据字典加载中...");
        Long start = System.currentTimeMillis();
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        IBaseTypeGroupService baseTypeGroupService = context.getBean(IBaseTypeGroupService.class);
        baseTypeGroupService.initAllBaseTypeGroups();
        Long end = System.currentTimeMillis();
        LOGGER.info(">>>>>数据字典加载结束，时间：" + (end - start) + "毫秒...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
