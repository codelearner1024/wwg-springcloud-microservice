package cn.wwg.microservice.order.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * websocket服务端
 */
@ServerEndpoint(value = "/websocketnew/{token}")
@Component
public class WebSocketNewServer {
    private Logger logger = LoggerFactory.getLogger(WebSocketNewServer.class);

    private static int onlineCount = 0;
    private static CopyOnWriteArraySet<WebSocketNewServer> webSocketSet = new CopyOnWriteArraySet<>();
    private static ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) {
        this.session = session;
        String sessionId = session.getId();
        webSocketSet.add(this);
        map.put(sessionId, token);
        incrOnlineCount();
        logger.debug("通道 {} 打开" ,sessionId);
        logger.debug("目前在线数：{}" , getOnlineCount());

    }


    @OnClose
    public void onClose(Session session) {
        String sessionId = session.getId();
        webSocketSet.remove(this);
        map.remove(sessionId);
        decrOnlineCount();
        logger.debug("连接{}关闭，当前在线数：{}", session.getId(), getOnlineCount());
    }

    /**
     * 用于接收客户端信息
     * @param session
     * @param msg
     */
    @OnMessage
    public void onMessage(Session session, String msg) {

    }

    @OnError
    public void onError(Throwable e) {
        logger.error("websocket error, reason:{}", e.getMessage());
    }

    private static synchronized void incrOnlineCount() {
        WebSocketNewServer.onlineCount++;
    }

    private static synchronized void decrOnlineCount() {
        if(WebSocketNewServer.onlineCount > 0) {
            WebSocketNewServer.onlineCount--;
        }
    }

    private static synchronized int getOnlineCount() {
        return WebSocketNewServer.onlineCount;
    }

    /**
     * 广播消息
     * 将消息发送到每一个已连接的客户端
     * @param message
     */
    public synchronized void broadcastMessage(String message, String token) {
        logger.debug("收到消息：{}", message);
        for (WebSocketNewServer clients : webSocketSet) {
            try {
                if(map.get(clients.session.getId()).equals(token)) {
                    clients.session.getBasicRemote().sendText(message);
                }
            } catch (IOException e) {
                logger.error("广播信息异常，原因：{}", e.getMessage());
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        session.getBasicRemote().sendText(message);
    }
}
