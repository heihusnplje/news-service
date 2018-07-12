package com.tigerjoys.news.service.unitls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class FileReadUtils {
	public static void main(String[] args) throws IOException {
		 PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream("D:/webmagic/all.txt"),"gbk"));
		File file = new File("D:/webmagic/romb/");
		File[] fileList = file.listFiles();
		for (File re : fileList) {
			System.out.println(re.getName());
             BufferedReader br = new BufferedReader(new FileReader(re));
             String s = null;
             while((s = br.readLine())!=null){
            	 printWriter.println(s);
             }
             br.close();
		}
		printWriter.close();
	}

}
