package com.tigerjoys.news.servicetest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tigerjoys.nbs.common.ActionResult;
import com.tigerjoys.nbs.common.utils.JsonHelper;
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
//	@Test
//	public void testHot() throws Exception {
//		ActionResult result = headHomeService.hotSkill();
//		System.err.println(JsonHelper.toJson(result));
//	}
//	

	
	
}
