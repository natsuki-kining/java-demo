package com.natsuki_kining.book.beauty.concurrency.part1.chapter2;


import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe 类的使用
 *
 * @Author : natsuki_kining
 * @Date : 2020/1/20 14:28
 */
public class Demo2_9_Unsafe {


    private static Unsafe unsafe;

    private static long stateOffset;

    private volatile long state = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
            stateOffset = unsafe.objectFieldOffset(Demo2_9_Unsafe.class.getDeclaredField("state"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Demo2_9_Unsafe demo = new Demo2_9_Unsafe();
        boolean result = unsafe.compareAndSwapInt(demo, stateOffset, 0, 1);
        System.out.println(result);
    }

}
