package com.invariant.devices.posiflex.service;

import com.invariant.devices.posiflex.service.serial.PortReader;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 */
public class SerialPortService {

    /**
     * В отсутствие DI, инстанс
     */
    private static SerialPortService instance;

    public static final String PORT_NAME = "/dev/ttyS5";

    public static SerialPortService getInstance() {
        if(instance == null){
            instance = new SerialPortService();
        }
        return instance;
    }

    public SerialPort getPort(){
        SerialPort serialPort = new SerialPort(PORT_NAME);
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.addEventListener(new PortReader(serialPort), SerialPort.MASK_RXCHAR);
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
        return serialPort;
    }

    /**
     * Аналог dmesg | grep tty
     * @return
     */
    public String[] getPortNames(){
         return SerialPortList.getPortNames();
    }
}
