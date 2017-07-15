package com.invariant.devices.service.serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 * 15.07.2017.
 */
public class PortReader implements SerialPortEventListener {

    private SerialPort serialPort;

    public PortReader(SerialPort serialPort){
        this.serialPort = serialPort;
    }

    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0){
            try {
                String data = serialPort.readString(event.getEventValue());
                System.out.print("Incoming data:");
                System.out.println(data.getBytes());
            }
            catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }

}
