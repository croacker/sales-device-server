package com.invariant.saleserver;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

/**
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.invariant.saleserver.http", "com.invariant.saleserver.service"})
@PropertySource("classpath:/application.properties")
public class ContextConfiguration {

    @Bean
    public SimpleHttpApplication asposeTestEntrypoint(){
        return new SimpleHttpApplication();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }

    @Bean
    public FileTypeMap getFileTypeMap(){
        return MimetypesFileTypeMap.getDefaultFileTypeMap();
    }
}
