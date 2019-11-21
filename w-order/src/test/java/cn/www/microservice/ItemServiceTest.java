package cn.www.microservice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import cn.wwg.microservice.order.OrderApplication;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=OrderApplication.class)
@WebAppConfiguration
public class ItemServiceTest {
	
	@Autowired
	LoadBalancerClient loadBalancerClient;

	@Test
	public void test() {
		String serviceId = "wwg-microservice-item";
		for (int i = 0; i < 100; i++) {
			ServiceInstance serviceInstance = loadBalancerClient.choose(serviceId);
			System.out.println("第" + i + "次：" + serviceInstance.getUri());
		}
	}

}

