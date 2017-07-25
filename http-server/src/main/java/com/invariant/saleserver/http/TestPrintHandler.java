package com.invariant.saleserver.http;

import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.devices.posiflex.service.ContentService;
import com.invariant.devices.posiflex.service.check.AuraCheck;
import com.invariant.devices.posiflex.service.check.Device;
import com.invariant.saleserver.service.DeviceService;
import com.invariant.saleserver.service.HttpService;
import com.invariant.saleserver.service.JsonService;
import com.invariant.saleserver.service.TaskService;
import com.invariant.saleserver.service.action.DeviceAction;
import com.invariant.saleserver.service.action.ResultStatus;
import com.invariant.saleserver.service.task.CheckData;
import com.invariant.saleserver.service.task.PrintCheckResult;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 *
 */
@Slf4j
@Component
public class TestPrintHandler implements HttpHandler {

    @Autowired
    private ContentService contentService;

    @Autowired
    private HttpService httpService;

    @Autowired @Getter
    private DeviceService deviceService;

    @Autowired @Getter
    private TaskService taskService;

    @Autowired @Getter
    private JsonService jsonService;

    @Value("${printer.print.timeout}")
    private int printTimeout;

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
        CheckData testCheckData = getTestData();
        PrintCheckResult result = printCheck(testCheckData);
        httpService.writeJson(httpExchange, jsonService.toJson(result));
    }



    protected PrintCheckResult printCheck(CheckData checkData) {
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

    private Printer getPrinter(String id) {
        return deviceService.getDevice(id);
    }

    private CheckData getTestData(){
        AuraCheck check = contentService.getTestCheck();
        CheckData testData = new CheckData();
        testData.setDevice(getTestDevice());
        testData.setCheck(check);
        return testData;
    }

    private Device getTestDevice(){
        return getDeviceService().getDefaultDevice();
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
