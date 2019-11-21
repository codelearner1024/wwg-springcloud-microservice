package cn.wwg.microservice.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import cn.wwg.microservice.order.feign.ItemFeignClient;
import cn.wwg.microservice.order.pojo.Item;
import cn.wwg.microservice.order.properties.OrderProperties;

@Service
public class ItemService {

	// Spring框架对RESTful方式的http请求做了封装，来简化操作
	@SuppressWarnings("unused")
	@Autowired
	private RestTemplate restTemplate;

	@SuppressWarnings("unused")
	@Autowired
	private OrderProperties orderProperties;

	@SuppressWarnings("unused")
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@Autowired
	private ItemFeignClient itemFeignClient;
	

	@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") //进行容错处理
	public Item queryItemById(Long id) {
		return itemFeignClient.queryItemById(id);
	}
	
	public Item queryItemByIdFallbackMethod(Long id) {  // 请求失败执行的方法
		return new Item(id, "查询商品信息出错", null, null, null);
	}
	
//	@HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod") //进行容错处理
//	public Item queryItemById(Long id) {
//		String serviceId = "wwg-microservice-item";
//		return this.restTemplate.getForObject("http://" + serviceId + "/item/" + id, Item.class);
//	}
	
//	public Item queryItemById(Long id) {
//		String serviceId = "wwg-microservice-item";
//		List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);
//		if(instances.isEmpty()){
//			return null;
//		}
//		// 为了演示，在这里只获取一个实例
//		ServiceInstance serviceInstance = instances.get(0);
//		String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//		return this.restTemplate.getForObject("http://" + url + "/item/" + id, Item.class);
//	}

	
//	public Item queryItemById(Long id) {
//		return this.restTemplate.getForObject(orderProperties.getItem().getUrl() + id, Item.class);
//	}

	// public Item queryItemById(Long id) {
	// return this.restTemplate.getForObject("http://127.0.0.1:8081/item/"
	// + id, Item.class);
	// }

}
