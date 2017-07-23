package com.invariant.saleserver.http;

import com.google.gson.Gson;
import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.saleserver.service.DeviceService;
import com.invariant.saleserver.service.HttpService;
import com.invariant.saleserver.service.TaskService;
import com.invariant.saleserver.service.action.DeviceAction;
import com.invariant.saleserver.service.action.ResultStatus;
import com.invariant.saleserver.service.task.CheckData;
import com.invariant.saleserver.service.task.PrintCheckResult;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component("printCheckHandler")
@Slf4j
public class PrintCheckHandler implements HttpHandler {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HttpService httpService;

    @Autowired
    private Gson gson;

    @Value("${printer.print.timeout}")
    private int printTimeout;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestMethod = httpExchange.getRequestMethod();
        if (requestMethod.equals("POST")) {
            post(httpExchange);
        } else {
            String mesage = "405 Method " + requestMethod + " not allowed ";
            httpService.writeMethodNotAlowed(httpExchange, mesage.getBytes());
        }
    }

    private void post(HttpExchange httpExchange) throws IOException {
        CheckData checkData = toCheckData(httpExchange.getRequestBody());
        PrintCheckResult result = printCheck(checkData);
        httpService.writeOk(httpExchange, toJson(result));
    }

    private PrintCheckResult printCheck(CheckData checkData) {
        PrintCheckResult result;
        String deviceId = checkData.getDevice().getId();
        Printer printer = getPrinter(deviceId);
        if (printer == null) {
            result = getError("Device by id " + deviceId + " not found.");
        } else {
            Future<PrintCheckResult> future = taskService.printCheck(checkData, printer);
            result = waitResult(future);
        }
        return result;
    }

    private PrintCheckResult waitResult(Future<PrintCheckResult> future) {
        PrintCheckResult result;
        try {
            result = future.get(printTimeout, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            log.error(e.getMessage(), e);
            result = getError("Timeout exception. Current value:"
                    + printTimeout + " milliseconds.");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            result = getError(e.getMessage());
        }
        return result;
    }

    private CheckData toCheckData(InputStream requestBody) throws IOException {
        String body = readBody(requestBody);
        return gson.fromJson(body, CheckData.class);
    }

    private String readBody(InputStream requestBody) throws IOException {
        return httpService.readBody(requestBody);
    }

    private Printer getPrinter(String id) {
        return deviceService.getDevice(id);
    }

    /**
     * Создать результат ошибку.
     *
     * @param
     * @return
     */
    private PrintCheckResult getError(String description) {
        PrintCheckResult result = new PrintCheckResult();
        result.setAction(DeviceAction.PRINT_CHECK.getName());
        result.setStatus(ResultStatus.ERROR.getName());
        result.setDescription(description);
        return result;
    }

    private byte[] toJson(Object object) {
        return gson.toJson(object).getBytes();
    }

}
