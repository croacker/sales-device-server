package com.invariant.saleserver.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.invariant.devices.service.SerialPortService;
import com.invariant.devices.service.serial.DataAccumulator;
import com.invariant.saleserver.SaleDeviceServerHttpApplication;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;
import java.io.PrintStream;

/**
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.invariant.saleserver.http", "com.invariant.saleserver.service"})
@PropertySource("classpath:config/application.properties")
public class ContextConfiguration {

    @Bean
    public SaleDeviceServerHttpApplication saleDeviceServerEntrypoint(){
        return new SaleDeviceServerHttpApplication();
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

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public SerialPortService getSerialPortService(){
        return new SerialPortService();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Gson getGson(){
        return new GsonBuilder().create();
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public DataAccumulator getDataAccumulator(){
        DataAccumulator dataAccumulator = new DataAccumulator();
        PrintStream printStream = new PrintStream(dataAccumulator);
        dataAccumulator.setPrintStream(printStream);
        return dataAccumulator;
    }
}
