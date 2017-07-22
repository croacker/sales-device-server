package com.invariant.saleserver.service;

import com.invariant.devices.posiflex.printer.Aura6800U;
import com.invariant.devices.posiflex.printer.Printer;
import com.invariant.devices.service.SerialPortService;
import com.invariant.devices.service.serial.DataAccumulator;
import com.invariant.devices.service.serial.SerialPortConfiguration;
import jssc.SerialPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SerialPortService serialPortService;

    @Autowired
    private DataAccumulator dataAccumulator;

    private Map<String, Printer> printers = new HashMap<>();

    public Printer getPrinter(String id){
        return printers.get(id);
    }

    @PostConstruct
    private void init() {
        SerialPortConfiguration configuration = getConfiguration();
        SerialPort serialPort = getSerialPort(configuration);
        Printer posiflexAura = new Aura6800U(serialPort);
        printers.put(printerAuraId, posiflexAura);
    }

    private SerialPortConfiguration getConfiguration() {
        return SerialPortConfiguration.getBuilder().setName(printerAuraPort).setBaudRate(printerAuraBaudRate)
                .setDataBits(printerAuraDataBits).setStopBits(printerAuraStopBits).setParity(printerAuraParity).build();
    }

    private SerialPort getSerialPort(SerialPortConfiguration configuration){
        return serialPortService.getPort(configuration, dataAccumulator.getPrintStream());
    }

}
