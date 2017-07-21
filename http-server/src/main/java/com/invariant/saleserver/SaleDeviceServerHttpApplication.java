package com.invariant.saleserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import com.invariant.saleserver.http.AppHttpServer;

import java.io.IOException;

/**
 * Точка входа для http-сервера
 */
@Slf4j
public class SaleDeviceServerHttpApplication {

    private static ApplicationContext getContext() {
        return ContextLoader.getInstance().getContext();
    }

    public static void main(String[] args) throws IOException {
        loadContext();
        SaleDeviceServerHttpApplication application = getContext().getBean(SaleDeviceServerHttpApplication.class);
        application.start();
    }

    private void start() throws IOException {
        log.info("Start Http-server.");
        getContext().getBean(AppHttpServer.class).start();
    }

    private static void loadContext() {
        log.info("Load application context...");
        ContextLoader.getInstance().load();
    }

}
