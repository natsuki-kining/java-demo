package com.natsuki_kining.gupao.v2.concurrent.chapter1.demo2;

public class Test {

    PrintProcessor printProcessor;

    public Test(){
        SaveProcessor saveProcessor = new SaveProcessor();
        saveProcessor.start();

        printProcessor = new PrintProcessor(saveProcessor);
        printProcessor.start();
    }

    public static void main(String[] args) {
        Request request = new Request();
        request.setName("natsuki kining");
        new Test().doTest(request);
    }

    public void doTest(Request request){
        printProcessor.processorRequest(request);
    }


}
