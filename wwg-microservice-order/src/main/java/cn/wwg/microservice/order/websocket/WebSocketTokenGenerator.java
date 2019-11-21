package cn.wwg.microservice.order.websocket;

import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.util.AssertionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/websockettoken")
public class WebSocketTokenGenerator {
    private Logger logger = LoggerFactory.getLogger(WebSocketTokenGenerator.class);

    @PostMapping(value = "/getToken")
    public String getToken(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        String userName ="";
        try{
            userName   = AssertionHolder.getAssertion().getPrincipal().getName();

        }catch (Exception e){
            logger.error("websocketToken getUserName erro");
            logger.error(e.getMessage());
        }
        return userName + "_" + sessionId;
    }
}
