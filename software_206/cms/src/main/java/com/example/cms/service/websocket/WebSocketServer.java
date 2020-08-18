
package com.example.cms.service.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArraySet;
/**
 * @author Yang
 */
@ServerEndpoint("/websocket")
@Component
public class WebSocketServer {
    private static int onlineCount=0;
    private  static int questionCount=0;

    @Override
    public boolean equals(Object o) {
        if (this == o)
        {return true;}
        if (o == null || getClass() != o.getClass()){ return false;}
        WebSocketServer that = (WebSocketServer) o;
        return Objects.equals(session, that.session);
    }

    @Override
    public int hashCode() {

        return Objects.hash(session);
    }

    /**
     * set线程安全，用来存放每个用户端对应的websocket对象
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet=new CopyOnWriteArraySet<>();
    /**
     * 与某个客户端的连接对话，用于给客户端发送信息
     */
    private Session session;

    public void sendMessage(String message)throws IOException{
        this.session.getBasicRemote().sendText(message);
    }

    public static synchronized int getOnlineCount(){
        return onlineCount;
    }

    public static synchronized void addOnlineCount(){
        WebSocketServer.onlineCount++;
    }

    public static synchronized  void subOnlineCount(){
        WebSocketServer.onlineCount--;
    }

    public static void sendInfo(String message) throws IOException{
        System.out.println(message);
        for(WebSocketServer item:webSocketSet){
            try{
                item.sendMessage(message);
            }catch (IOException e){
                continue;
            }
        }
    }

    @OnOpen
    public void onOpen(Session session)throws IOException{
        this.session=session;
        webSocketSet.add(this);
        addOnlineCount();
        System.out.println("有新连接加入,当前连接人数为: "+getOnlineCount());
    }

    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);
        subOnlineCount();
        System.out.println("有一连接关系！当前在线人数为"+getOnlineCount());
    }

    /**
     * 当客户端发来信息时
     * @param message
     * @param session
     */
    @OnMessage
    public void onMessage(String message,Session session){
        System.out.println("来自客户端的信息"+message);
        for(WebSocketServer webSocketServer:webSocketSet){
            try{
                if("提问".equals(message))
                {
                    questionCount++;
                    message=String.valueOf(questionCount);
                    webSocketServer.sendMessage(message);
                }
                else if("下一组".equals(message)){
                    questionCount=0;
                    message=String.valueOf(questionCount);
                    webSocketServer.sendMessage(message);
                }
                else if("提问人数-1".equals(message))
                {
                    questionCount--;
                    message=String.valueOf(questionCount);
                    webSocketServer.sendMessage(message);
                }
                else if("断开".equals(message))
                {
                    questionCount=0;
                    onOpen(session);
                }
                else {
                    webSocketServer.sendMessage(message);
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
}
