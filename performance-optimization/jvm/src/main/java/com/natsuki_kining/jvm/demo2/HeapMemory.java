package com.natsuki_kining.jvm.demo2;

import org.openjdk.jol.info.ClassLayout;

/**
 * -XX:+UseCompressedOops  开启指针压缩
 * -XX:-UseCompressedOops  关闭指针压缩
 */
public class HeapMemory {
    private static Object obj1 = new Object();

    public static void main(String[] args) {
        Object obj2 = new Object();
        System.out.println(ClassLayout.parseInstance(obj1).toPrintable());
        System.out.println(ClassLayout.parseInstance(obj2).toPrintable());
    }
}