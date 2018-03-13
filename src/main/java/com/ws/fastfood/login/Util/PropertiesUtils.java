package com.ws.fastfood.login.Util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


/**   
*    
* 项目名称：login   
* 类名称：PropertiesUtils   
* 类描述：   
* 创建人：wangshuo   
* 创建时间：2018年3月13日 下午4:50:52   
* 修改人：   
* 修改时间：   
* 修改备注：   
* @version    
*    
*/
public class PropertiesUtils {
	 /**
     * (注意：加载的是src下的文件,如果在某个包下．请把包名加上)
     * @return
     */
    public static Properties getProperties(String sourcesFileName){
        Properties properties = new Properties();
        String savePath = Thread.currentThread().getContextClassLoader().getResource(sourcesFileName).getPath();
        try{
            InputStream inputStream = new BufferedInputStream(new FileInputStream(savePath));
            properties.load(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  properties;
    }
  

    /**
     * 获取属性文件的数据 根据key获取值
     * @param key
     * @return
     */
    public static String findPorpertyByKey(String key,String sourcesFileName){

        try {
            Properties pro = getProperties(sourcesFileName);
            return pro.getProperty(key);
        }catch (Exception e){
            return "";
        }
    }

}
