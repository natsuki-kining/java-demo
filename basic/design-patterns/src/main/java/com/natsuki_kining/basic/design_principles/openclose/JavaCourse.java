package com.natsuki_kining.basic.design_principles.openclose;

/**
 * TODO
 *
 * @author natsuki_kining
 * @date 2021/5/2 23:37
 **/
public class JavaCourse implements ICourse{
    
    public Course getCourse() {
        return new Course("1","java",10000D);
    }
}
