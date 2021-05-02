package com.natsuki_kining.basic.design_principles.dependencyinversion;

/**
 * TODO
 *
 * @author natsuki_kining
 * @date 2021/5/2 23:43
 **/
public class Student {

    public void study(ICourse course){
        course.study();
    }
}
