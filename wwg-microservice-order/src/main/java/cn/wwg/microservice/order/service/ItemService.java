package cn.wwg.microservice.order.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cn.wwg.microservice.order.pojo.Item;
import cn.wwg.microservice.order.properties.OrderProperties;

@Service
public class ItemService {

	// Spring框架对RESTful方式的http请求做了封装，来简化操作
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OrderProperties orderProperties;

	@Autowired
	private DiscoveryClient discoveryClient;

	
	public Item queryItemById(Long id) {
		String serviceId = "wwg-microservice-item";
		List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
		if(instances.isEmpty()){
			return null;
		}
		// 为了演示，在这里只获取一个实例
		ServiceInstance serviceInstance = instances.get(0);
		String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
		return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
	}

	
//	public Item queryItemById(Long id) {
//		return this.restTemplate.getForObject(orderProperties.getItem().getUrl() + id, Item.class);
//	}

	// public Item queryItemById(Long id) {
	// return this.restTemplate.getForObject("http://127.0.0.1:8081/item/"
	// + id, Item.class);
	// }

}
