package com.tigerjoys.news.servicetest;

import java.net.URL;
import java.util.Enumeration;

import org.junit.Test;

public class MethodText {
	@Test
	public void testYdHttp() throws Exception {
		 ClassLoader classLoader = Thread.currentThread()
	                .getContextClassLoader();
		  Enumeration<URL> resources = classLoader.getResources("com");
		  while(resources.hasMoreElements()){
			  URL resource = resources.nextElement();
			  System.out.println(resource.toString());
		  }
		
	}
	
}
