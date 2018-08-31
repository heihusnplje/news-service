package com.tigerjoys.news.servicetest;

import java.io.File;
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
	
	@Test
	public void testClassLoader() throws Exception { 
		String json = JsonHelper.toJson(ClassLoader.getSystemClassLoader());
		System.out.println("json="+json);
		
		String json2 = JsonHelper.toJson(Thread.currentThread().getContextClassLoader());
		System.out.println("json2="+json2);
		
	    String s = System.getProperty("java.class.path");
		
	    System.out.println("s="+s);
	    
	    System.out.println(MethodText.class.getClassLoader().toString());
	    System.out.println("=====================================");
	    
	    String ext = System.getProperty("java.ext.dirs");
	    System.out.println("java.ext.dirs="+ext);
	    System.out.println("File.pathSeparator="+File.pathSeparator);
	    System.out.println("File.pathSeparator="+File.pathSeparatorChar);
	    System.out.println("=====================================");
	    String bootClassPath = System.getProperty("sun.boot.class.path");
	    System.out.println("bootClassPath="+bootClassPath);
	}
	
	
	@Test
	public void testSystemTime() throws Exception{
		long time =System.nanoTime();
		System.out.println("time="+time);
		Thread.sleep(1000);
		long time2 =System.nanoTime();
		System.out.println("time2="+time2);
		long sub = time2-time;
		System.out.println("sub="+sub);
		System.out.println(sub/1000/1000);
		
	}
	
	
}
