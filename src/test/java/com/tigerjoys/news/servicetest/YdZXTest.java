package com.tigerjoys.news.servicetest;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.news.service.dto.HuaWeiPackageDto;
import com.tigerjoys.news.service.processor.YdHttpclientProcessor;
import com.tigerjoys.news.unitls.BaseTestConfig;


/**
 * 首页业务测试
 * @author yangjunming
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:/spring/applicationContext.xml"})
public class YdZXTest extends BaseTestConfig {
	
	private static final Logger logger = LoggerFactory.getLogger(YdZXTest.class);
	

//	@Autowired
//	private IIndexService indexService;
//	
//
//	
	@Test
	public void testYdHttp() throws Exception {
		
		YdHttpclientProcessor.excl("best", 0, 200);
	}
	
	
	@Test
	public void testMethod() throws Exception {
		HuaWeiPackageDto dto = new HuaWeiPackageDto();
		dto.setFirmware("abced");
		Method method =dto.getClass().getMethod("getFirmware", (Class<?>[])null);
		String re = (String)method.invoke(dto, (Object[])null);
		System.out.println("========="+re);
		
	}
	
	
	
	 
	
	
}
