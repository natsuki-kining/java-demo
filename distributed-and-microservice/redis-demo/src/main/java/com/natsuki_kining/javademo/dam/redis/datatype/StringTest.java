package com.natsuki_kining.javademo.dam.redis.datatype;

import com.natsuki_kining.javademo.dam.redis.utils.JedisUtil;

import java.util.Arrays;
import java.util.List;

/**
 * 可以用来存储字符串、整数、浮点数
 */
public class StringTest {

    public static void main(String[] args) {
        //批量设置
        String mset = JedisUtil.getJedisUtil().mset("key1", "value1", "key2", "value2");
        System.out.println("mset key1 value1 key2 value");
        System.out.println();

        //批量获取
        List<String> mget = JedisUtil.getJedisUtil().mget("key1", "key2");
        System.out.println("mget key1 key2:"+mget.toString());
        System.out.println();
        
        //获取长度
        Long keyLength = JedisUtil.getJedisUtil().strLen("key1");
        System.out.println("strLen key1:"+keyLength);
        System.out.println();

        //字符串追求内容
        Long append = JedisUtil.getJedisUtil().append("key1", "append value");
        System.out.println("get key1:"+JedisUtil.getJedisUtil().get("key1"));
        System.out.println("append key1 append value:"+append);
        System.out.println();

        //获取指定范围的字符
        String getrange = JedisUtil.getJedisUtil().getrange("key1", 0, 5);
        System.out.println("getrange key1 0 5:"+getrange);
        System.out.println();

        //（整数）值递增
        System.out.println("get intkey:"+JedisUtil.getJedisUtil().get("intKey"));
        Long intKey = JedisUtil.getJedisUtil().incr("intKey");
        System.out.println("incr intkey:"+intKey);
        intKey = JedisUtil.getJedisUtil().incr("intKey");
        System.out.println("incr intkey:"+intKey);
        System.out.println("get intkey:"+JedisUtil.getJedisUtil().get("intKey"));
        System.out.println();


        System.out.println("get intkey:"+JedisUtil.getJedisUtil().get("intKey"));
        intKey = JedisUtil.getJedisUtil().incrBy("intKey",100);
        System.out.println("incrby intkey 100:"+intKey);
        System.out.println("get intkey:"+JedisUtil.getJedisUtil().get("intKey"));
        System.out.println();

        //（浮点数）值递增
        String f1 = JedisUtil.getJedisUtil().set("f", "1.23");
        System.out.println(f1);

    }


}
