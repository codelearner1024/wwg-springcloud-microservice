package cn.wwg.microservice.apigateway.route;

import org.springframework.cloud.netflix.zuul.filters.discovery.PatternServiceRouteMapper;
import org.springframework.context.annotation.Bean;

public class RouteConfig {

	@Bean
	public PatternServiceRouteMapper serviceRouteMapper() {
		// 调用构造函数 new PatternServiceRouteMapper(servicePattern, routePattern)
		// servicePattern指定微服务的正则
		// routePattern指定路由的正则
		return new PatternServiceRouteMapper("(?<name>^.+)-(?<version>v.+$)", "${version}/${name}");
		//microservice-provider-user-v1 映射到/v1/microservice-provider-user/**
	}
}
