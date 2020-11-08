package com.natsuki_kining.gupao.v1.microservices.spring.cloud.config.client.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring 自定义 事件监听器
 */
public class SpringEventListenerDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();

        // 注册监听器
        context.addApplicationListener(
                new ApplicationListener<MyApplicationEvent>() {
                    /**
                     * 监听器得到事件
                     * @param event
                     */
                    @Override
                    public void onApplicationEvent(MyApplicationEvent event) {

                        System.out.println("接收到事件：" + event.getSource() +" @ "+event.getApplicationContext());
                    }
                });

        context.refresh();
        // 发布事件
        context.publishEvent(new MyApplicationEvent(context,"Hello,World"));
        context.publishEvent(new MyApplicationEvent(context,1));
        context.publishEvent(new MyApplicationEvent(context,new Integer(100)));

    }

    private static class MyApplicationEvent extends ApplicationEvent {

        private final ApplicationContext applicationContext;

        public MyApplicationEvent(ApplicationContext applicationContext, Object source) {
            super(source);
            this.applicationContext=applicationContext;
        }

        public ApplicationContext getApplicationContext() {
            return applicationContext;
        }
    }

}
