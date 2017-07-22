package com.invariant.saleserver.configuration;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.nio.file.Paths;
import java.util.Arrays;

@Configuration
@PropertySource("classpath:/config/devices.json")
public class DevicesConfiguration {

    @Bean
    public ConfigurationProvider getDeviceConfiguration(){
        ConfigFilesProvider configFilesProvider = () -> Arrays.asList(Paths.get("config/devices.json"));
        ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);
        return new ConfigurationProviderBuilder()
                .withConfigurationSource(source).build();
    }

}
