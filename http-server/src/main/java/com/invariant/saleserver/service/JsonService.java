package com.invariant.saleserver.service;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@Slf4j
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JsonService {

    @Autowired
    private Gson gson;

    public byte[] toJson(Object object) {
        return gson.toJson(object).getBytes(StandardCharsets.UTF_8);
    }

    public <T> T fromJson(String json, Class<T> clazz){
        return gson.fromJson(json, clazz);
    }


}
