package com.ubsoft.framework.core.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;

/**
 * 应用程序配置类
 */

@Configuration
public class AppConfig {
    private static Map<String, String> props = new HashMap();
    @Autowired
    private Environment env;
    /**
     * 获取值
     * @param key
     * @return
     */
    public String getProperty(String key) {
        String value = env.getProperty(key);
        if (value == null) {
            value = props.get(key);
        }
        return value;
    }

    /**
     * 添加设置值
     *
     * @param key
     * @param value
     */
    public void setProperty(String key, String value) {
        props.put(key, value);
    }


    public boolean isDebug(){
        return true;
    }
}
