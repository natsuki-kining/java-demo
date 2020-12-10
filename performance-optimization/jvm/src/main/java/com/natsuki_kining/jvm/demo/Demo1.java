package com.natsuki_kining.jvm.demo;

/**
 * TODO
 *
 * @Author natsuki_kining
 * @Date 2021/2/24 23:15
 **/
public class Demo1 {

    int a = 1;
    private int b = 2;
    protected int c = 3;
    public int d = 4;

    static int e = 5;
    private static int f = 6;

    final static int g = 7;
    private static int h = 8;

    String str = "a";

    public static void staticMethod(){
        System.out.println("a");
    }

    public void method(){
        method(str, b);
    }

    public void method(String str,int b){
        this.str = str;
        this.b = b;
    }

    public static void main(String[] args) {
        staticMethod();
    }
}
