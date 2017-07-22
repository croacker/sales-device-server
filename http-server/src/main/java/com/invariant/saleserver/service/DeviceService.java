package com.invariant.saleserver.service;

import com.google.common.base.Splitter;
import com.invariant.devices.posiflex.printer.Aura6800U;
import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.devices.service.SerialPortService;
import com.invariant.devices.service.serial.SerialPortConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Service
@Scope("singleton")
@Slf4j
public class DeviceService {

    @Value("${printer.aura.id}")
    private String printerAuraId;
    @Value("${printer.aura.port}")
    private String printerAuraPort;
    @Value("${printer.aura.baudRate}")
    private String printerAuraBaudRate;
    @Value("${printer.aura.dataBits}")
    private String printerAuraDataBits;
    @Value("${printer.aura.stopBits}")
    private String printerAuraStopBits;
    @Value("${printer.aura.parity}")
    private String printerAuraParity;

    private Map<String, Printer> printers = new HashMap<>();

    @PostConstruct
    private void init() {
        SerialPortService serialPortService = new SerialPortService();
        getConfiguration();
//        Printer posiflexAura = new Aura6800U();
    }

    private SerialPortConfiguration getConfiguration() {
        return SerialPortConfiguration.getBuilder().setName(printerAuraPort).setBaudRate(printerAuraBaudRate)
                .setDataBits(printerAuraDataBits).setStopBits(printerAuraStopBits).setParity(printerAuraParity).build();
    }

}
