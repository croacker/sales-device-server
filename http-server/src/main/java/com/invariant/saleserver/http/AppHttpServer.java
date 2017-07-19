package com.invariant.saleserver.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import com.invariant.saleserver.ContextLoader;

/**
 *
 */
@Component
@Slf4j
public class AppHttpServer {

    private Map<String, HttpHandler> routes = new HashMap<String, HttpHandler>();

    @Value("${server.port}")
    private Integer port;

    private static ApplicationContext getContext(){
        return ContextLoader.getInstance().getContext();
    }

    public void start() throws IOException {
        HttpServer server = createServer();
        server.start();
        log.info("Server startup at:" + port + " port");
    }

    private void init(){
        routes.put("/",  getContext().getBean(IndexHandler.class));
        routes.put("/css", getContext().getBean(StaticHandler.class));
        routes.put("/js", getContext().getBean(StaticHandler.class));
        routes.put("/fonts", getContext().getBean(StaticHandler.class));
        routes.put("/img", getContext().getBean(StaticHandler.class));
    }

    private HttpServer createServer() throws IOException {
        init();
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        for (Map.Entry<String, HttpHandler> route: routes.entrySet()){
            server.createContext(route.getKey(), route.getValue());
        }
        server.setExecutor(null);
        return server;
    }
}
