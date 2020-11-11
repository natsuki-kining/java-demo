package com.natsuki_kining.springboot.demo.volatiledemo;

/**
 * 传递性规则
 *
 */
public class Demo2 {

    int a=0;
    volatile boolean flag=false;

    public void writer(){ //线程A
        a=1;             //1
        flag=true;       //2
    }

    // 2 happens-before 3
    public void reader(){
        if(flag){  //3
            int x=a; //4
        }
        //3 happens-before 4
    }

}
