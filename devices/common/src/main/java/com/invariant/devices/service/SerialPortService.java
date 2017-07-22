package com.invariant.devices.service;

import com.invariant.devices.service.serial.PortReader;
import com.invariant.devices.service.serial.SerialPortConfiguration;
import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;

/**
 * 15.07.2017.
 */
@Slf4j
public class SerialPortService {

    /**
     * В отсутствие DI, инстанс
     */
    private static SerialPortService instance;

    public static final String PORT_NAME = "/dev/ttyS5";

    public static SerialPortService getInstance() {
        if (instance == null) {
            instance = new SerialPortService();
        }
        return instance;
    }

    public SerialPort getPort() {
        SerialPortConfiguration configuration = SerialPortConfiguration.getBuilder().setName(PORT_NAME)
                .setBaudRate("9600").setDataBits("8").setStopBits("1")
                .setParity("0").build();
        return getPort(configuration, System.out);
    }

    /**
     * @param configuration
     * @return
     */
    public SerialPort getPort(SerialPortConfiguration configuration, PrintStream printStream) {
        SerialPort serialPort = new SerialPort(configuration.getName());
        try {
            serialPort.openPort();
            serialPort.setParams(configuration.getBaudRate(),
                    configuration.getDataBits(),
                    configuration.getStopBits(),
                    configuration.getParity());
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.addEventListener(new PortReader(serialPort, printStream), SerialPort.MASK_RXCHAR);
        } catch (SerialPortException e) {
            log.error(e.getMessage(), e);
        }
        return serialPort;
    }

    /**
     * Аналог dmesg | grep tty
     *
     * @return
     */
    public String[] getPortNames() {
        return SerialPortList.getPortNames();
    }

}
