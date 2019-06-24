package cn.wwg.microservice.order.handler;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@Component
@ServerEndpoint(value ="/websocket")
public class WebSocketHandler {

	private Session session;
	@OnOpen
	public void onOpen(Session session) {
		System.out.println("已连接");
	}
	
	@OnMessage
	public void onMessage(String msg) {
		System.out.println(msg);
	}
	
	@OnClose
	public void onClose(Throwable e) {
		e.printStackTrace();
	}
	
}

