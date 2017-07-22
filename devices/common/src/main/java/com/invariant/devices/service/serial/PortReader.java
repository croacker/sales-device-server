package com.invariant.devices.service.serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import java.io.PrintStream;

/**
 * 15.07.2017.
 */
public class PortReader implements SerialPortEventListener {

    private SerialPort serialPort;

    private PrintStream printStream;

    public PortReader(SerialPort serialPort, PrintStream printStream){
        this.serialPort = serialPort;
        this.printStream = printStream;
    }

    public void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR() && event.getEventValue() > 0){
            try {
                String data = serialPort.readString(event.getEventValue());
                printStream.print("Incoming data:");
                printStream.println(data.getBytes());
            }
            catch (SerialPortException ex) {
                printStream.println(ex);
            }
        }
    }

}
