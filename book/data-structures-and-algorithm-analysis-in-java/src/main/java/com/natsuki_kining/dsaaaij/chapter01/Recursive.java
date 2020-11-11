package com.natsuki_kining.dsaaaij.chapter01;

/**
 * 递归简论
 *
 * @Author natsuki_kining
 * @Date 2020/12/15 17:18
 **/
public class Recursive {

    /**
     * f(x)=2f(x -1) +X
     * f(0) = 0
     * f(1) = 1
     * f(2)= 6
     *
     * @param x
     * @return
     */
    public static int f(int x) {
        if (x == 0) {
            return 0;
        } else {
            return 2 * f(x - 1) + x * x;
        }
    }

    public static int bad(int n) {
        if (n == 0) {
            return 0;
        } else {
            return bad(n / 3 + 1) + n - 1;
        }
    }

    public static void printOut(int n) {
        if (n >= 10) {
            printOut(n / 10);
        }
        printOut(n % 10);
    }

    public static void main(String[] args) {
        System.out.println(Recursive.f(2));
        //System.out.println(Recursive.bad(2));
        System.out.println(Recursive.f(11));
    }
}
