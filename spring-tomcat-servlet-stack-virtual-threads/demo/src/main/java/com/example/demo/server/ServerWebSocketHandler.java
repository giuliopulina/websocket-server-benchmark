package com.example.demo.server;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class ServerWebSocketHandler extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(ServerWebSocketHandler.class);

    @Override
    public void handleTextMessage(WebSocketSession callerSession, @NotNull TextMessage toForward) throws IOException {
        String content = toForward.getPayload();
        callerSession.sendMessage(new TextMessage(content));
    }
}
