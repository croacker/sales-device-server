package com.invariant.saleserver.http;

import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.saleserver.service.*;
import com.invariant.saleserver.service.action.DeviceAction;
import com.invariant.saleserver.service.action.ResultStatus;
import com.invariant.saleserver.service.task.CheckData;
import com.invariant.saleserver.service.task.PrintCheckResult;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Cleanup;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component("printCheckHandler")
@Slf4j
public class PrintCheckHandler implements HttpHandler {

    @Autowired @Getter
    private DeviceService deviceService;

    @Autowired @Getter
    private TaskService taskService;

    @Autowired @Getter
    private HtmlService htmlService;

    @Autowired @Getter
    private HttpService httpService;

    @Autowired @Getter
    private JsonService jsonService;

    @Value("${printer.print.timeout}")
    private int printTimeout;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestMethod = httpExchange.getRequestMethod();
        if (requestMethod.equals("POST")) {
            post(httpExchange);
        } else if (requestMethod.equals("GET")) {
            get(httpExchange);
        } else {
            String mesage = "405 Method " + requestMethod + " not allowed ";
            httpService.writeMethodNotAlowed(httpExchange, mesage.getBytes());
        }
    }

    private void post(HttpExchange httpExchange) throws IOException {
        CheckData checkData = toCheckData(httpExchange.getRequestBody());
        PrintCheckResult result = printCheck(checkData);
        httpService.writeJson(httpExchange, jsonService.toJson(result));
    }

    private void get(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        @Cleanup OutputStream outputStream = httpExchange.getResponseBody();
        @Cleanup InputStream inputStream = getHtmlService().getCheckExample();
        final byte[] buffer = new byte[0x10000];
        int count;
        while ((count = inputStream.read(buffer)) >= 0) {
            outputStream.write(buffer,0,count);
        }
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
        return jsonService.fromJson(body, CheckData.class);
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

}
