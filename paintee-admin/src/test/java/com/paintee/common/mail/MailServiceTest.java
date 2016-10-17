package com.paintee.common.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations ={"classpath:context-paintee-admin-repository.xml",
		"classpath:mybatis-config-paintee-admin-repository.xml",
		"file:src/main/webapp/WEB-INF/paintee-admin-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class MailServiceTest {
	@Autowired
	private MailService mailService;

	@Test
	public void test() {
		try {
			mailService.sendMail("javahaja@hotmail.com", "ddddd", "cccccccccccccc");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
