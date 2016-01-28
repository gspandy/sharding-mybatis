package com.gracebrother;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gracebrother.service.UserService;

/**
 * @author bin
 */
public class DemoTest {

	private ClassPathXmlApplicationContext appCtx;

	@Before
	public void init() {
		appCtx = new ClassPathXmlApplicationContext(new String[] { "spring.xml", "spring-mybatis.xml" });
	}

	@Test
	public void test_add() {
		UserService userService = appCtx.getBean("userService", UserService.class);
		try {
			userService.insert();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
