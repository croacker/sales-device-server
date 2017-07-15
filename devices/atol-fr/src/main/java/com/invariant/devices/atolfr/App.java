package com.invariant.devices.atolfr;

import com.invariant.devices.service.SerialPortService;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 */
public class App {

    public static void main(String[] args) {
        SerialPort serialPort = SerialPortService.getInstance().getPort();
        try {
            serialPort.writeString("123456789012345678901234567890");
            serialPort.writeString("123456789012345678901234567890");
            serialPort.writeString("123456789012345678901234567890");
            serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

}
