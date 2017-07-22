package com.invariant.devices.service.serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintStream;

/**
 * 15.07.2017.
 */
@Slf4j
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
            catch (SerialPortException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

}
