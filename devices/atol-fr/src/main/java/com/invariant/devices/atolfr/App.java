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
            serialPort.writeBytes(new byte[]{ 27 });
            serialPort.closePort();
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

}
