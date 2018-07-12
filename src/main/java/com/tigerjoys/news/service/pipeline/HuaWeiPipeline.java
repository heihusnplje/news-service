package com.tigerjoys.news.service.pipeline;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tigerjoys.news.service.dto.HuaWeiPackageDto;

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
public class HuaWeiPipeline extends FilePersistentBase implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private String subPath ;
    /**
     * create a FilePipeline with default path"/data/webmagic/"
     */
    public HuaWeiPipeline() {
        setPath("/data/webmagic/");
    }

    public HuaWeiPipeline(String path,String subPath) {
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
            List<HuaWeiPackageDto> list = (List<HuaWeiPackageDto>)resultItems.get("data");
            if(list ==null || list.size()==0){
            	return ;
            }
            for(HuaWeiPackageDto re: list){
            	 printWriter.println("insert into t_aa_rom ( firmware, type, update_time, full_url, public_url, type_url_1,type_url_2,type_url_3,type_url_4,type_url_5,type_url_6,type_url_7) values('"+re.getFirmware()+"','"+re.getType()+"','"+re.getDate()+"','"+re.getFullUrl()+"','"+re.getPublicUrl()+"','"+(re.getTypeUrl().size()>0?re.getTypeUrl().get(0):"")+"','"+(re.getTypeUrl().size()>=2?re.getTypeUrl().get(1):"")+"','"+(re.getTypeUrl().size()>=3?re.getTypeUrl().get(2):"")+"','"+(re.getTypeUrl().size()>=4?re.getTypeUrl().get(3):"")+"','"+(re.getTypeUrl().size()>=5?re.getTypeUrl().get(4):"")+"','"+(re.getTypeUrl().size()>=6?re.getTypeUrl().get(5):"")+"','"+(re.getTypeUrl().size()>=7?re.getTypeUrl().get(6):"")+"');");
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
