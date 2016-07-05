package com.paintee.admin.test.service;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations ={"classpath:context-paintee-admin-repository.xml",
									"classpath:mybatis-config-paintee-admin-repository.xml",
									"file:src/main/webapp/WEB-INF/paintee-admin-servlet.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestServiceTest {
	private final static Logger logger = LoggerFactory.getLogger(TestServiceTest.class);

	@Autowired
	private DataSource dataSource;

	@Test
	public void test() {
		if(dataSource == null) {
			logger.debug("dataSource is null");
		} else {
			logger.debug("dataSource is not null.{}", dataSource);
		}
	}

}
