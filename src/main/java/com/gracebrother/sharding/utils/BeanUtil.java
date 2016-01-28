package com.gracebrother.sharding.utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class BeanUtil {
	public static Object getBean(ServletContextEvent context,String bean){
		ServletContext servletContext=context.getServletContext();
        ApplicationContext ctx=WebApplicationContextUtils.getWebApplicationContext(servletContext);
        return ctx.getBean(bean);
	}
}