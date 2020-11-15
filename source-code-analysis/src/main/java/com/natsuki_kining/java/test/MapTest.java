package com.natsuki_kining.java.test;

import com.natsuki_kining.java.util.HashMap7;
import com.natsuki_kining.java.util.HashMap8;
import com.natsuki_kining.java.util.concurrent.ConcurrentHashMap;
import com.natsuki_kining.java.util.concurrent.ConcurrentMap;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2021/1/25 21:29
 **/
public class MapTest {

    public static void main(String[] args) {
        HashMap8<String,String> map8 = new HashMap8<>();
        map8.put("","");
        map8.get("");

        HashMap7<String,String> map7 = new HashMap7<>();
        map7.put("","");
        map7.get("");

        ConcurrentHashMap<String,String> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("","");
        concurrentMap.get("");
    }
}
