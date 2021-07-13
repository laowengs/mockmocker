package com.laowengs.mocker.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
public class ServerInfo implements ApplicationListener<WebServerInitializedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ServerInfo.class);


    private int serverPort;
    private String serverUrl;

    public String getUrl() {
        return serverUrl;
    }
 
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        this.serverPort = event.getWebServer().getPort();
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
           logger.error("获取ip异常",e);
        }
        this.serverUrl = "http://" + (address == null ? "127.0.0.1" : address.getHostAddress()) + ":" + this.serverPort;
    }
 
}