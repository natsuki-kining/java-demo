package com.natsuki_kining.jvm.demo;

class Person {
    private final static String hobby = "Programming";
    private static String address;
    private static final Object obj = new Object();
    private final double salary = 100;
    private final String name = "Jack";
    private int age;

    public static int calc(int op1, int op2) {
        op1 = 3;
        int result = op1 + op2;
        Object obj = new Object();
        return result;
    }

    public static void main(String[] args) {
        calc(1, 2);
    }

    public void say() {
        System.out.println("person say...");
    }
}