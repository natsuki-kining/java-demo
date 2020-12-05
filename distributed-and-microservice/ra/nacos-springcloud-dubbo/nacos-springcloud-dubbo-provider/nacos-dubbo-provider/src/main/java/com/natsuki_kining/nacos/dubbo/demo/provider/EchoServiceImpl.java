
package com.natsuki_kining.nacos.dubbo.demo.provider;

import com.natsuki_kining.nacos.api.EchoService;
import org.apache.dubbo.config.annotation.Service;

@Service(protocol = "dubbo")
class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String message) {
		return "[echo] Hello, " + message;
	}

}
