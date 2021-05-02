package com.natsuki_kining.basic.design_principles.dependencyinversion;

/**
 * TODO
 *
 * @author natsuki_kining
 * @date 2021/5/2 23:41
 **/
public class Test {

    public static void main(String[] args) {
        Student student = new Student();
        student.study(new JavaCourse());
        student.study(new PythonCourse());
    }
}
