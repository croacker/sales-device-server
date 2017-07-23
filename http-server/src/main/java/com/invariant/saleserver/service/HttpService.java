package com.invariant.saleserver.service;

import com.sun.net.httpserver.HttpExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HttpService {

    public void writeOk(HttpExchange httpExchange, byte[] data) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        httpExchange.getResponseBody().write(data);
        httpExchange.getResponseBody().close();
    }

    public void writeMethodNotAlowed(HttpExchange httpExchange, byte[] message) throws IOException {
        httpExchange.sendResponseHeaders(405, 0);
        httpExchange.getResponseBody().write(message);
        httpExchange.getResponseBody().close();
    }

    public String readBody(InputStream requestBody) throws IOException {
        InputStreamReader isr =
                new InputStreamReader(requestBody, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(isr);
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}
