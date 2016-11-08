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
public class HtmlContentBuilderTest {
	@Autowired
	private HtmlContentBuilder htmlContentBuilder;

	@Test
	public void test() {
		ConfirmMailVO confirmMailVO = new ConfirmMailVO();
		confirmMailVO.setTitle("title");
		confirmMailVO.setConfirmUrl("http://naver.com");
		confirmMailVO.setSenderName("sender");
		System.out.println("1111");
		try {
			System.out.println(htmlContentBuilder.getSignupConfirmMail(confirmMailVO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
