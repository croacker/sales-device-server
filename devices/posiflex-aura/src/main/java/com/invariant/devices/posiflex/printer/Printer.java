package com.invariant.devices.posiflex.printer;

import com.invariant.devices.posiflex.service.check.AuraCheck;
import com.invariant.devices.service.serial.SerialPortConfiguration;

/**
 *
 */
public interface Printer {

    SerialPortConfiguration getConfiguration();

    void print(AuraCheck check);

}
