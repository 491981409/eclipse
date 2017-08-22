package com.xxx.socket;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/echo",configurator=GetHttpSessionConfigurator.class)
public class EndPointServer {

    public static  List<EndPointServer>  sessionList= new ArrayList<EndPointServer>();
    public Session  session ;

     @OnOpen
     public void onOpen(Session session,EndpointConfig config) throws IOException {
        this.session = session;
        sessionList.add(this);
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        httpSession.setAttribute("scoketSessionId", session.getId()); 
    }

     @OnMessage
     public void onMessage(Session session,String message) {
     }

     @OnError
     public void onError(Throwable t) {
     }

     @OnClose
     public void onClose(Session session, CloseReason reason) {
         sessionList.remove(this);
     } 


}