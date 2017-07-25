package com.invariant.saleserver.http;

import com.google.gson.Gson;
import com.invariant.saleserver.service.HttpService;
import com.invariant.saleserver.service.JsonService;
import com.invariant.saleserver.service.TaskService;
import com.invariant.saleserver.service.task.CheckData;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component("checkListHandler")
@Slf4j
public class CheckListHandler implements HttpHandler {

    @Autowired
    private TaskService taskService;

    @Autowired
    private HttpService httpService;

    @Autowired
    private JsonService jsonService;

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
        Map<String, CheckData> printedChecks = taskService.getPrintedChecks();
        List<CheckData> checks = printedChecks.values().stream().collect(Collectors.toList());
        httpService.writeJson(httpExchange, jsonService.toJson(checks));
    }


}
