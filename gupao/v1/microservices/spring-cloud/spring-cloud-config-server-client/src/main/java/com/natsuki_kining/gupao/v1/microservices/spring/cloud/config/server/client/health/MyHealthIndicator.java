package com.natsuki_kining.gupao.v1.microservices.spring.cloud.config.server.client.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @author mercyblitz
 * @email mercyblitz@gmail.com
 * @date 2017-10-25
 **/
public class MyHealthIndicator extends AbstractHealthIndicator {

    @Override
    protected void doHealthCheck(Health.Builder builder)
            throws Exception {
        builder.down().withDetail("MyHealthIndicator","Down");
    }
}
