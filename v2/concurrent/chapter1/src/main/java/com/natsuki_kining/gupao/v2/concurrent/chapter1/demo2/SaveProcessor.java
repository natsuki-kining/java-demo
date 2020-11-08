package com.natsuki_kining.gupao.v2.concurrent.chapter1.demo2;

import java.util.concurrent.LinkedBlockingQueue;

public class SaveProcessor extends Thread implements RequestProcessor {

    LinkedBlockingQueue<Request> linkedBlockingQueue = new LinkedBlockingQueue<Request>();

    @Override
    public void processorRequest(Request request) {
        linkedBlockingQueue.add(request);
    }

    @Override
    public void run() {
        for(;;){
            try{
                Request request = linkedBlockingQueue.take();
                System.out.println("save data:"+request);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
