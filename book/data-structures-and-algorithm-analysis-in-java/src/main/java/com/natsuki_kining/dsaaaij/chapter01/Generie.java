package com.natsuki_kining.dsaaaij.chapter01;

/**
 * 泛型
 *
 * @Author natsuki_kining
 * @Date 2020/12/15 19:17
 **/
public class Generie {

    public static void main(String[] args) {
        MemoryCell m1 = new MemoryCell();
        m1.write("37");
        System.out.println("m1: " + m1.read());

        MemoryCell m = new MemoryCell();
        m.write(new Integer(37));
        Integer wrapperVal = (Integer) m.read();
        int val = wrapperVal.intValue();
        System.out.println("m2: " + wrapperVal);

    }

    public static class MemoryCell {
        private Object storedValue;

        public Object read() {
            return storedValue;
        }

        public void write(Object x) {
            storedValue = x;
        }
    }

}
