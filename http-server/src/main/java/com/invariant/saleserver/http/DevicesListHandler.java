package com.invariant.saleserver.http;

import com.google.gson.Gson;
import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.saleserver.service.DeviceService;
import com.invariant.saleserver.service.HttpService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component("devicesListHandler")
@Slf4j
public class DevicesListHandler implements HttpHandler {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private HttpService httpService;

    @Autowired
    private Gson gson;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestMethod = httpExchange.getRequestMethod();
        if (requestMethod.equals("GET")) {
            get(httpExchange);
        } else {
            String mesage = "405 Method " + requestMethod + " not allowed ";
            httpService.writeMethodNotAlowed(httpExchange, mesage.getBytes());
        }
    }

    private void get(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        httpExchange.getResponseBody().write("Devices:\n".getBytes());
        List<Printer> printers = getDevices();
        httpExchange.getResponseBody().write(gson.toJson(printers).getBytes());
        httpExchange.getResponseBody().close();
    }

    private Printer getDevice(String id) {
        return deviceService.getDevice(id);
    }

    private List<Printer> getDevices() {
        return deviceService.getDevices();
    }

}
