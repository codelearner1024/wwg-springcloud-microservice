package cn.wwg.microservice.order.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="autoDefine") //以autoDefine开头的配置被匹配到
public class OrderProperties {
	
	private ItemProperties item = new ItemProperties();

	public ItemProperties getItem() {
		return item;
	}

	public void setItem(ItemProperties item) {
		this.item = item;
	}
	
}

