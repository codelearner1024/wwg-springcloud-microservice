package cn.wwg.microservice.order.config;

import javax.websocket.server.ServerEndpointConfig;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
//--------------------- 
//作者：羲和_望舒 
//来源：CSDN 
//原文：https://blog.csdn.net/qq_35017509/article/details/86243089 
//版权声明：本文为博主原创文章，转载请附上博文链接！

/**
 *  以websocketConfig.java注册的bean是由自己管理的，需要使用配置托管给spring管理
 */
public class MySpringConfigurator extends ServerEndpointConfig.Configurator implements ApplicationContextAware {

    private static volatile BeanFactory context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MySpringConfigurator.context = applicationContext;
    }

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        return context.getBean(clazz);
    }
}
