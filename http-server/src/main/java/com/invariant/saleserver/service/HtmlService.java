package com.invariant.saleserver.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 *
 */
@Service
@Slf4j
public class HtmlService {

    @Autowired @Getter
    private ResourceService resourceService;

    public InputStream getIndex(){
        return getResourceService().get("/view/index.html");
    }

    public InputStream getApi(){
        return getResourceService().get("/view/api.html");
    }

}
