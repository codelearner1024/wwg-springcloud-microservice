package cn.wwg.microservice.order.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
@ConditionalOnWebApplication
public class WebSocketConfig {

	@Bean
	public ServerEndpointExporter getServerEndpointExporter() {
		return new ServerEndpointExporter();
	}
	
	@Bean
    public MySpringConfigurator mySpringConfigurator() {
        return new MySpringConfigurator();
    }
}

