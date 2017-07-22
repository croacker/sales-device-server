package com.invariant.saleserver.http;

import com.google.gson.Gson;
import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.devices.posiflex.service.check.AuraCheck;
import com.invariant.devices.posiflex.service.check.AuraCheckHeader;
import com.invariant.devices.posiflex.service.check.AuraCheckRow;
import com.invariant.saleserver.service.DeviceService;
import com.invariant.saleserver.service.TaskService;
import com.invariant.saleserver.service.task.CheckData;
import com.invariant.saleserver.service.task.PrintCheckResult;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    private Gson gson;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        if(httpExchange.getRequestMethod().equals("GET")){
            httpExchange.sendResponseHeaders(405, 0);
            httpExchange.getResponseBody().close();
        }else if(httpExchange.getRequestMethod().equals("POST")){
            post(httpExchange);
        }else{
            httpExchange.sendResponseHeaders(405, 0);
            httpExchange.getResponseBody().close();
        }
    }

    private void post(HttpExchange httpExchange) throws IOException {
        CheckData checkData = toCheck(httpExchange.getRequestBody());
        Printer printer = getPrinter(checkData.getDevice().getId());
        Future<PrintCheckResult> future = taskService.printCheck(checkData, printer);
        PrintCheckResult printCheckResult = waitResult(future);

        httpExchange.sendResponseHeaders(200, 0);
        httpExchange.getResponseBody().write("PRINT CHECK".getBytes());
        httpExchange.getResponseBody().write("\n".getBytes());
        httpExchange.getResponseBody().write(gson.toJson(printCheckResult).getBytes());
        httpExchange.getResponseBody().close();
    }

    private PrintCheckResult waitResult(Future<PrintCheckResult> future) {
        PrintCheckResult result = new PrintCheckResult();
        try {
            result = future.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            result.setStatus("error");
        } catch (ExecutionException e) {
            log.error(e.getMessage(), e);
            result.setStatus("error");
        } catch (TimeoutException e) {
            log.error(e.getMessage(), e);
            result.setStatus("error");
        }
        return result;
    }

    private CheckData toCheck(InputStream requestBody) throws IOException {
        String body = readBody(requestBody);
        return gson.fromJson(body, CheckData.class);
    }

    private String readBody(InputStream requestBody) throws IOException {
        InputStreamReader isr =
                new InputStreamReader(requestBody,"utf-8");
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }

    private Printer getPrinter(String id){
        return deviceService.getPrinter(id);
    }

    private AuraCheck getCheck(){
        AuraCheck check = new AuraCheck();

        AuraCheckHeader header = new AuraCheckHeader();
        header.setOrderNumber("7_970");
        header.setPosNumber("1");
        header.setCheckNumber("77000972");
        header.setDateTime("28/06/2017 18:49:57");
        header.setWaiter("Сергей");
        header.setTable("3.5");
        check.setHeader(header);

        AuraCheckRow row = new AuraCheckRow();
        row.setGuest("ГОСТЬ 2");
        row.setCount("1");
        row.setGoodName("КАРТОФЕЛЬ ФРИ");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setCount("1");
        row.setGoodName("САЛАТ ФРАНЦУЗСКИЙ");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setGuest("ГОСТЬ 3");
        row.setCount("1");
        row.setGoodName("САЛАТ ПТИЧКА");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setGuest("ГОСТЬ 4");
        row.setCount("1");
        row.setGoodName("САЛАТ ФРАНЦУЗСКИЙ");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setGuest("ГОСТЬ 6");
        row.setCount("1");
        row.setGoodName("САЛАТ ГРЕЧЕСКИЙ");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setCount("2");
        row.setGoodName("ПОЗЫ КЛАССИЧЕСКИЕ");
        check.addRow(row);
        return check;
    }
}
