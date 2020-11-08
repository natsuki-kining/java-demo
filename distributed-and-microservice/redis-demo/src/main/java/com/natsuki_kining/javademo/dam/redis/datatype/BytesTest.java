package com.natsuki_kining.javademo.dam.redis.datatype;

import com.natsuki_kining.javademo.dam.redis.utils.JedisUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;


/**
 * 可以存文件
 */
public class BytesTest {
    public static void main(String[] args) throws IOException {
        System.out.println(Charset.defaultCharset());
        File file = new File("E:/test.txt");
        byte[] bytes = FileUtils.readFileToByteArray(file);

        String str = new String(bytes);
        System.out.println("str"+str);

        JedisUtil.getJedisUtil().set("fileString", str);
    }
}
