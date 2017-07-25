package com.invariant.devices.posiflex.printer;

import com.invariant.devices.service.serial.SerialPortConfiguration;
import jssc.SerialPort;

/**
 *
 */
public class Aura6900UB extends CommonPrinter{

    public Aura6900UB(SerialPortConfiguration configuration, SerialPort serialPort) {
        super(configuration, serialPort);
    }

}
