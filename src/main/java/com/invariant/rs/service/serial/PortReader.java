package com.invariant.rs.service.serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
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
                //serialPort.writeString("Get data");
                System.out.println(data);
            }
            catch (SerialPortException ex) {
                System.out.println(ex);
            }
        }
    }

}
