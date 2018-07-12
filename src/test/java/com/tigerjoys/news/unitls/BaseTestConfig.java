package com.tigerjoys.news.unitls;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public abstract class BaseTestConfig {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected ApplicationContext appContext;
	
	@Autowired
	protected MockHttpServletRequest request;
	
	@Autowired
	protected MockHttpSession session;
	
	@Autowired
	protected MockHttpServletResponse response;
	
	@Before
	public void classInit(){
		logger.info("before test init!");
		MockitoAnnotations.initMocks(this);
	}
	
	@After
	public void classDestory(){
		logger.info("after test destory!");
	}

}
