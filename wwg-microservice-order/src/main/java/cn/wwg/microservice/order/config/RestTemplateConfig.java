package cn.wwg.microservice.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;


/**
 *类描述:RestTemplate配置
 *
 *@Author:WWG
 *@date:2018年6月25日
 *@Version:1.0
 */
@Configuration
public class RestTemplateConfig {
	
	
	@Bean // 向Spring容器中定义RestTemplate对象
	public RestTemplate restTemplate(){
		return new RestTemplate(new OkHttp3ClientHttpRequestFactory());
	}

	
	/*@Bean("template")
	public RestTemplate RestTemplate() {
		
		RestTemplateBuilder builder = new RestTemplateBuilder();
		RestTemplate template = builder.build();
		template.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		return template;
	}*/
}

