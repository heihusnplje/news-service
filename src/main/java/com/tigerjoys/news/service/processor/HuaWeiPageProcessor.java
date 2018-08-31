package com.tigerjoys.news.service.processor;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.tigerjoys.news.service.dto.HuaWeiPackageDto;
import com.tigerjoys.news.service.pipeline.HuaWeiPipeline;
import com.tigerjoys.news.service.unitls.HttpClientUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class HuaWeiPageProcessor implements PageProcessor {
	private static final Logger logger = LoggerFactory.getLogger(HuaWeiPageProcessor.class);
    private List<HuaWeiPackageDto>  resultList = new ArrayList<>();
	    private Site site = Site.me().setDomain("update.hicloud.com:8180").setUserAgent("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; Win64; x64; Trident/4.0; Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1);").setRetryTimes(3).setSleepTime(1000).setTimeOut(50000);

	    @Override
	    public void process(Page page) {
	    	 
	    	List<String> list = page.getJson().jsonPath("firmwares").all();
	        for(String re:list){
	        	JSONObject entity = JSONObject.parseObject(re);
	        	String firmware = entity.getString("firmware");
	        	String type = entity.getString("type");
	        	String date = entity.getString("date");
	        	String path = entity.getString("filelist_link");
	        	String subPath = path.substring(0, path.lastIndexOf("filelist.xml"));
	        	String data = HttpClientUtils.get(path, 5000, 10000, 10000);
	        	if(data==null || "".equals(data)){
	        		continue;
	        	}
	        	Html html = new Html(data);
	        	String commonPackage = html.xpath("//vendorInfo[@name='common']/@package").get();
	        	String commonSubPath= html.xpath("//vendorInfo[@name='common']/@subpath").get();
	         
	        	String publicPackage = html.xpath("//vendorInfo[@name='public']/@package").get();
	        	String publicSubPath= html.xpath("//vendorInfo[@name='public']/@subpath").get();
	        
	        	List<String> pack = html.xpath("//vendorInfo/@name").all();
	        	String mobileType = "";
	        	
	        	List<String> typeUrlList = new ArrayList<>();
	        	for(String mobileName:pack){
	        		if(!"common".equals(mobileName) && !"public".equals(mobileName)){
	        			mobileType = mobileName;
	        			String typePackage = html.xpath("//vendorInfo[@name='"+mobileType+"']/@package").get();
	    	        	String typeSubPath = html.xpath("//vendorInfo[@name='"+mobileType+"']/@subpath").get();
	    	        	String typeUrl = subPath+("".equals(typeSubPath)?"":typeSubPath+"/")+typePackage;
	    	        	typeUrlList.add(typeUrl);
	        		}
	        	}
	        	
	        	String fullUrl = commonPackage==null ?"":(subPath+("".equals(commonSubPath)?"":commonSubPath+"/")+commonPackage);
	        	String publicUrl = publicPackage==null ?"":(subPath+("".equals(publicSubPath)?"":publicSubPath+"/")+publicPackage);
	        	
	        	HuaWeiPackageDto dto = new HuaWeiPackageDto();
	        	dto.setFirmware(firmware);
	        	dto.setType(type);
	        	dto.setDate(date);
	        	dto.setFullUrl(fullUrl);
	        	dto.setPublicUrl(publicUrl);
	        	dto.setTypeUrl(typeUrlList);
	        	resultList.add(dto);
	        	//logger.info("-----");
	        	page.putField("data", resultList);
	        	page.putField("size", resultList.size());
	        	
	        }
	    }

	    @Override
	    public Site getSite() {
	        return site;
	    }
	    
	public static void main(String[] args) {
		for (int i = 1; i < 144; i++) {
			Spider.create(new HuaWeiPageProcessor())
					.addUrl("http://pro-teammt.ru/projects/hwff/info/v2/provider.php?GetFirmwaresJson&page="+i)
					// .addPipeline(new FilePipeline("D:/webmagic/"))
					.addPipeline(new HuaWeiPipeline("D:/webmagic/romb/", ""+i)).thread(5).run();
			logger.info("-----执行---"+i);
			
		}
		logger.info("-----执行结束---");
		
	}
	}
