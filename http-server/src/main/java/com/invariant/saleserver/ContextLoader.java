package com.invariant.saleserver;

import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 */
public class ContextLoader {

    private static ContextLoader instance;

    @Getter
    private ApplicationContext context;

    public static ContextLoader getInstance(){
        if(instance == null){
            instance = new ContextLoader();
        }
        return instance;
    }

    public void load(){
        if(context == null){
            context = new AnnotationConfigApplicationContext(ContextConfiguration.class);
        }
    }

}
