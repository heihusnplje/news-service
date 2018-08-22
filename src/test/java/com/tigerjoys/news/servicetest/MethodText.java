package com.tigerjoys.news.servicetest;

import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

import com.tigerjoys.nbs.common.utils.JsonHelper;

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
	
	
	@Test
	public void testSystem() throws Exception {
		Map<String,String> map = System.getenv();
		map.forEach((k,v)->{
			System.out.println(k+"    "+v);
		});
		
	}
	
	@Test
	public void testBooleanClass() throws Exception {
		SecurityManager securityManager = System.getSecurityManager();
		System.out.println(JsonHelper.toJson(securityManager));
		if(Boolean.TYPE.equals(boolean.class)){
			System.out.println("相等");
		}
		if(!Boolean.TYPE.equals(Boolean.class)){
			System.out.println("不相等");
		}
		
		System.out.println(void.class);
		
	}
	
	@Test
	public void testSystemGetProperties() throws Exception {
		 Properties properties = System.getProperties();
		 System.out.println(JsonHelper.toJson(properties));
		 String s = System.getProperty("java.security.manager");
		 System.out.println("s="+s);
		 System.setProperty("aaaaa", "ccccc");
		 System.out.println(System.getProperty("aaaaa"));
		 
		 
	}
	
	
	@Test
	public void testCurrentLog() throws Exception {
		textLog();
		printLog();   //获取当前行
	}
	
	public void textLog(){
		 StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		 int i=0;
	        for(StackTraceElement s : stackTrace){
	            System.out.println("id:"+i+",类名：" + s.getClassName() + "  ,  java文件名：" + s.getFileName() + ",  当前方法名字：" + s.getMethodName() + ""
	            		+ " , 当前代码是第几行：" + s.getLineNumber() + ", " );
	            i++;
	        }
	}
	
	
	public void printLog(){
		StackTraceElement s = Thread.currentThread().getStackTrace()[2];
		System.out.println("类名：" + s.getClassName() + "  ,  java文件名：" + s.getFileName() + ",  当前方法名字：" + s.getMethodName() + ""
                + " , 当前代码是第几行：" + s.getLineNumber() + ", " );
	}
	
	
}
