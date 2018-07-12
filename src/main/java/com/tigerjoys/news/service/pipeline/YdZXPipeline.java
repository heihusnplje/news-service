package com.tigerjoys.news.service.pipeline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.tigerjoys.news.service.dto.YdzxDto;

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
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(getFile(path + ".txt")),"UTF-8"));
            //printWriter.println("url:\t" + resultItems.getRequest().getUrl());
            List<YdzxDto> list = (List<YdzxDto>)resultItems.get("data");
            if(list ==null || list.size()==0){
            	return ;
            }
            for(YdzxDto re: list){
            	 printWriter.println(JSON.toJSONString(re));
            }
            /*
            for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
                if (entry.getValue() instanceof Iterable) {
                    Iterable value = (Iterable) entry.getValue();
                    printWriter.println(entry.getKey() + ":");
                    for (Object o : value) {
                        printWriter.println(o);
                    }
                } else {
                    printWriter.println(entry.getKey() + ":\t" + entry.getValue());
                }
            }
            */
           
        } catch (IOException e) {
            logger.warn("write file error", e);
        }finally{
        	if(printWriter!=null){
        		printWriter.close();
        	}
        }
    }
}
