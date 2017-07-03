package com.invariant.rs.service;

import com.invariant.rs.service.serial.PortReader;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 */
public class SerialPortService {

    private static SerialPortService instance;

    public static SerialPortService getInstance() {
        if(instance == null){
            instance = new SerialPortService();
        }
        return instance;
    }

    public SerialPort getSerialPort(){
        //dmesg | grep tty
        ///dev/ttyUSB0
        SerialPort serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN |
                    SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.addEventListener(new PortReader(serialPort), SerialPort.MASK_RXCHAR);
            //serialPort.writeString("Get data");
        }
        catch (SerialPortException ex) {
            System.out.println(ex);
        }
        return serialPort;
    }
}
