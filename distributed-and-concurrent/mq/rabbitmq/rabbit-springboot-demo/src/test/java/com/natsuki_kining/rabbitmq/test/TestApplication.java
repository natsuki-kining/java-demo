package com.natsuki_kining.rabbitmq.test;

import com.natsuki_kining.rabbitmq.provider.MyProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestApplication {

	@Autowired
	MyProvider provider;

	@Test
	public void contextLoads() {
		provider.send();
	}

}
