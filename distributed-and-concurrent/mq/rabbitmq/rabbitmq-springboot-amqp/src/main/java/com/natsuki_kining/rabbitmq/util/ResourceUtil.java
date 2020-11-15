package com.natsuki_kining.rabbitmq.util;

import java.util.ResourceBundle;

/**
 * @Author: qingshan
 *
 */
public class ResourceUtil {
    private static final ResourceBundle resourceBundle;

    static{
        resourceBundle = ResourceBundle.getBundle("rabbitmq");
    }

    public static String getKey(String key){
        return resourceBundle.getString(key);
    }

}
