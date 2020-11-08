package com.natsuki_kining.javademo.dam.redis.utils;

import java.util.ResourceBundle;

/**
 * 配置文件读取工具
 */
public class ResourceUtil {
    private static final ResourceBundle resourceBundle;

    static{
        resourceBundle = ResourceBundle.getBundle("redis");
    }

    public static String getKey(String key){
        return resourceBundle.getString(key);
    }

}
