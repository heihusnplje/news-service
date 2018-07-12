package com.tigerjoys.news.service.unitls;

/**
 * Hello world!
 *
 */
public class YdZXSptokenUtils 
{
    public static void main( String[] args )
    {
    	System.out.println(System.currentTimeMillis());
    	System.out.println(YdZXSptokenUtils.sptoken("hot", 10, 20));
    }
    
    public static String sptoken(String type,int start,int end) {
    	String value = "";
    	String re = "_"+type+"_"+start+"_"+end+"_";
    	for(int i=0;i<re.length();i++){
    		int c = 10 ^ re.charAt(i);
    		value+=String.valueOf((char)c);
    	}
    	return EncodeURIUtils.encodeURIComponent(value);
    }

}
