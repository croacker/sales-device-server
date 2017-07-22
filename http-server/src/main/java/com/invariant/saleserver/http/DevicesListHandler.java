package com.invariant.saleserver.http;

import com.google.gson.Gson;
import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.saleserver.service.DeviceService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("devicesListHandler")
@Slf4j
public class DevicesListHandler implements HttpHandler {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private Gson gson;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if (httpExchange.getRequestMethod().equals("GET")) {
            get(httpExchange);
        } else {
            httpExchange.sendResponseHeaders(405, 0);
            httpExchange.getResponseBody().close();
        }
    }

    private void get(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        httpExchange.getResponseBody().write("Devices:\n".getBytes());
        Printer printer = getPrinter("posiflexAura");
        httpExchange.getResponseBody().write(gson.toJson(printer).getBytes());
        httpExchange.getResponseBody().close();
    }

    private Printer getPrinter(String id) {
        return deviceService.getPrinter(id);
    }

}
