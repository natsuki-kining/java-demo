package com.natsuki_kining.basic.design_principles.openclose;

/**
 * TODO
 *
 * @author natsuki_kining
 * @date 2021/5/2 23:38
 **/
public class JavaDiscountCourse implements ICourse {

    public Course getCourse() {
        return new Course("1","java",10000D*0.6);
    }
}
