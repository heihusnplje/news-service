package com.tigerjoys.news.service.pipeline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jca.context.SpringContextResourceAdapter;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tigerjoys.nbs.common.utils.JsonHelper;
import com.tigerjoys.nbs.common.utils.Tools;
import com.tigerjoys.nbs.mybatis.core.utils.SpringBeanApplicationContext;
import com.tigerjoys.news.inter.contract.IYdzxInfoContract;
import com.tigerjoys.news.inter.contract.impl.YdzxInfoContractImpl;
import com.tigerjoys.news.inter.contract.impl.YdzxTestContractImpl;
import com.tigerjoys.news.inter.entity.YdzxInfoEntity;
import com.tigerjoys.news.inter.entity.YdzxTestEntity;
import com.tigerjoys.news.service.dto.YdzxDto;
import com.tigerjoys.news.service.unitls.SpringContextUtils;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

/**
 * Store results in files.<br>
 *
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class YdZXPipeline extends FilePersistentBase implements Pipeline {
	


    private Logger logger = LoggerFactory.getLogger(getClass());
    private String subPath ;
    /**
     * create a FilePipeline with default path"/data/webmagic/"
     */
    public YdZXPipeline() {
        setPath("/data/webmagic/");
    }

    public YdZXPipeline(String path,String subPath) {
        setPath(path);
        this.subPath = subPath;
    }

    public String getSubPath() {
		return subPath;
	}

	public void setSubPath(String subPath) {
		this.subPath = subPath;
	}

	@Override
    public void process(ResultItems resultItems, Task task) {
        String path = this.path + PATH_SEPERATOR + this.subPath;
        YdzxInfoContractImpl ydzxInfoContract = SpringContextUtils.getContext().getBean(YdzxInfoContractImpl.class);
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path + ".txt")),"UTF-8"));
            List<YdzxDto> list = (List<YdzxDto>)resultItems.get("data");
            if(list ==null || list.size()==0){
            	return ;
            }
            for(YdzxDto re: list){
            	YdzxInfoEntity entity = new YdzxInfoEntity();
            	 entity.setTitle(re.getTitle());
            	 entity.setExtra(JsonHelper.toJson(re.getExtra()));
            	 entity.setCreate_time(Tools.getDateTime(re.getDate(), "yyyy-MM-dd HH:mm:ss"));
            	 entity.setDocid(re.getDocid());
            	 entity.setMeta(re.getMeta());
            	 entity.setCtype(re.getCtype());
            	 entity.setDtype(re.getDtype());
            	 entity.setImpid(re.getImpid());
            	 entity.setPageid(re.getPageid());
            	 entity.setItemid(re.getItemid());
            	 entity.setDisplay_flag(re.getDisplay_flag());
            	 entity.setFeedback_forbidden(re.getFeedback_forbidden()!=null && re.getFeedback_forbidden()?1:0);
            	 entity.setSecurity(re.getSecurity());
            	 entity.setTags(JsonHelper.toJson(re.getTags()));
            	 entity.setSummary(re.getSummary());
            	 entity.setB_political(re.getB_political()!=null && re.getB_political()?1:0);
            	 entity.setImage_urls(JsonHelper.toJson(re.getImage_urls()));
            	 entity.setSource(re.getSource());
            	 entity.setUrl(re.getUrl());
            	 entity.setMtype(re.getMtype());
            	 entity.setCategory(re.getCategory());
            	 entity.setEdit_cover(re.getEdit_cover()!=null && re.getEdit_cover()?1:0);
            	 entity.setComment_count(re.getComment_count());
            	 entity.setImage(re.getImage());
            	 entity.setUpload_images(JsonHelper.toJson(re.getUpload_images()));
            	 entity.setDis_recommend(re.getDis_recommend()!=null && re.getDis_recommend() ?1:0);
            	 entity.setAlike(re.getLike());
            	 entity.setUp(re.getUp());
            	 entity.setAuth(re.getAuth()?1:0);
            	 entity.setIs_gov(re.getIs_gov()?1:0);
            	 entity.setContent_type(re.getContent_type());
            	 entity.setCard_position(JsonHelper.toJson(re.getCard_position()));
            	 entity.setTitle_sn(re.getTitle_sn());
            	 entity.setWemedia_info(JsonHelper.toJson(re.getWemedia_info()));
            	 try {
            		 ydzxInfoContract.insert(entity);
				} catch (Exception e) {
					logger.info("ydzxinfo error:"+JsonHelper.toJson(entity),e);
				}
            }
        
           
        } catch (IOException e) {
            logger.warn("write file error", e);
        }finally{
        	if(printWriter!=null){
        		printWriter.close();
        	}
        }
    }
}
