package com.invariant.saleserver.http;

import com.google.gson.Gson;
import com.invariant.saleserver.service.HtmlService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.Cleanup;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 */
@Component("indexHttpHandler")
@Slf4j
public class IndexHandler implements HttpHandler {

    @Autowired @Getter
    private HtmlService htmlService;

    @Autowired @Getter
    private Gson gson;

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        log.info("Process request:" + getGson().toJson(httpExchange.getRequestHeaders()));
        httpExchange.sendResponseHeaders(200, 0);
        @Cleanup OutputStream outputStream = httpExchange.getResponseBody();
        @Cleanup InputStream inputStream = getHtmlService().getIndex();
        final byte[] buffer = new byte[0x10000];
        int count;
        while ((count = inputStream.read(buffer)) >= 0) {
            outputStream.write(buffer,0,count);
        }
    }
}
