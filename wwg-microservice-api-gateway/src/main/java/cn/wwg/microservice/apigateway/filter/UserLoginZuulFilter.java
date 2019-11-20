package cn.wwg.microservice.apigateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;


@Component
public class UserLoginZuulFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {

		return true;
		
	}

	@Override
	public Object run() { //业务逻辑
		RequestContext currentContext = RequestContext.getCurrentContext();
		HttpServletRequest request = currentContext.getRequest();
		String token = request.getParameter("token");
		if (StringUtils.isEmpty(token)) {
			currentContext.setSendZuulResponse(false);
			currentContext.setResponseStatusCode(401);//设置http相应状态码
			return null;
		}
		return null;
	}

	@Override
	public String filterType() {
		return "pre";//在路由到真正的路径前执行的过滤器
		
	}

	@Override
	public int filterOrder() {
		return 0;//优先级设为最高 0
	}



}
