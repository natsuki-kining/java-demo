package com.natsuki_kining.gupao.v2.distributed.netty.bio_teacher_demo;


public class Calculator {
    public static int cal(String expression) throws Exception {
        char op = expression.charAt(1);
        switch (op) {
            case '+':
                return (expression.charAt(0) - 48) + (expression.charAt(2) - 48);
            case '-':
                return (expression.charAt(0) - 48) - (expression.charAt(2) - 48);
            case '*':
                return (expression.charAt(0) - 48) * (expression.charAt(2) - 48);
            case '/':
                return (expression.charAt(0) - 48) / (expression.charAt(2) - 48);
        }

        throw new Exception("Calculator error");

    }


}