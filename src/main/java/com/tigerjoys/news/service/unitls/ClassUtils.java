package com.tigerjoys.news.service.unitls;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import us.codecraft.webmagic.processor.PageProcessor;

/**
 * @function:给一个接口，返回这个接口的所有实现类的工具类
 * @author yankunpeng
 * @date 2015-2-6下午03:13:43
 * @mailto yan095650@163.com
 */
@SuppressWarnings("all")
public class ClassUtils {

    /**
     * @function: 查询某个接口的所有实现类
     * @param c
     *            接口的class
     * @param rootPackageList
     *            项目中根包的集合，也就是包名的最上级，如com,cn等等，要视具体项目而定
     * @return List<Class> 满足条件的类集合
     * @author yankunpeng
     * @date 2015-2-6下午04:08:10
     * @mailto yan095650@163.com
     */
    public static List<Class> getAllClassByInterface(Class c,
            List<String> rootPackageList) {
        List<Class> returnClassList = new ArrayList<Class>();
        // 如果不是一个接口，则不做处理
        if (c.isInterface()) {
            try {
                List<Class> allClass = getClasses(rootPackageList);
                for (int i = 0; i < allClass.size(); i++) {
                    // Class.isAssignableFrom()是用来判断一个类Class1和另一个类Class2是否相同或是另一个类的子类或接口。
                    System.out.println(allClass.get(i).toString());
                	if ((!allClass.get(i).isInterface())// 是否是类
                            && (c.isAssignableFrom(allClass.get(i)))// 是否实现了同一个接口
                            && (!c.equals(allClass.get(i)))) {// 不是本身
                        returnClassList.add(allClass.get(i));
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return returnClassList;
    }

    /**
     * @function:查找包内的所有类和接口
     * @param rootPackageList
     *            项目中根包的集合，也就是包名的最上级，如com,cn等等，要视具体项目而定
     * @return List<Class> 满足条件的类集合
     * @throws ClassNotFoundException
     * @throws IOException
     * @author yankunpeng
     * @date 2015-2-6下午05:15:09
     * @mailto yan095650@163.com
     */
    private static List<Class> getClasses(List<String> rootPackageList)
            throws ClassNotFoundException, IOException {
        ArrayList<Class> classes = new ArrayList<Class>();
        ClassLoader classLoader = Thread.currentThread()
                .getContextClassLoader();
        for (String packageName : rootPackageList) {
        	String path = packageName.replace('.', '/');
        	//String path = packageName;
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<File>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }
        }

        return classes;
    }

    /**
     * @function:查找制定路径下的.class文件
     * @param directory
     *            路径
     * @param packageName
     *            包名
     * @return List<Class> 满足条件的类集合
     * @throws ClassNotFoundException
     * @author yankunpeng
     * @date 2015-2-6下午05:17:12
     * @mailto yan095650@163.com
     */
    private static List<Class> findClasses(File directory, String packageName)
            throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + "."
                        + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName
                        + '.'
                        + file.getName().substring(0,
                                file.getName().length() - 6)));
            }
        }
        return classes;
    }

    public static void main(String[] args) {
    	URL url= Thread.currentThread().getContextClassLoader().getResource("");
    	System.out.println(url.getPath());
        File file= new File(url.getPath());
        List<String> list = new ArrayList<String>();
        for(File re:file.listFiles()){
        	if(re.isDirectory()){
        		list.add(re.getName());
        	}else{
        		System.out.println(re.getName());
        	}
        }
    	
       // list.add("com");
       // list.add("cn");
        System.out.println(getAllClassByInterface(PageProcessor.class, list));
    }
}