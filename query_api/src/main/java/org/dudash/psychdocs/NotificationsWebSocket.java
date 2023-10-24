package org.dudash.psychdocs;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.websocket.EncodeException;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.Objects.requireNonNull;

@ServerEndpoint("/notifications/{clientId}")
@ApplicationScoped
public class NotificationsWebSocket {

    Map<String, Session> sessions = new ConcurrentHashMap<>(); 

    @OnOpen
    public void onOpen(Session session, @PathParam("clientId") String clientId) {
        System.out.println("onOpen> " + clientId);
        sessions.put(clientId, session);
        broadcast("_socket_debug:Client c_" + clientId + " joined");
    }

    @OnClose
    public void onClose(Session session, @PathParam("clientId") String clientId) {
        System.out.println("onClose> " + clientId);
        sessions.remove(clientId);
        broadcast("_socket_debug:Client c_" + clientId + " left");
    }

    @OnError
    public void onError(Session session, @PathParam("clientId") String clientId, Throwable throwable) {
        System.out.println("onError> " + clientId + ": " + throwable);
        sessions.remove(clientId);
        broadcast("_socket_debug:Client c_" + clientId + " left on error: " + throwable);
    }

    @OnMessage
    public void onMessage(String message, @PathParam("clientId") String clientId) {
        System.out.println("onMessage> " + clientId + ": " + message);
        broadcast("c_"+clientId + ":" + message); // broadcast client messages too
    }

    // broadcast will send to all sessions
    private void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result ->  {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }
}