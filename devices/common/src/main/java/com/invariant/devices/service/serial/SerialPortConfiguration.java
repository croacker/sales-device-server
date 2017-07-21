package com.invariant.devices.service.serial;

/**
 * 15.07.2017.
 */
public interface SerialPortConfiguration {

    String getName();

    int getBaudRate();

    int getDataBits();

    int getStopBits();

    int getParity();


}
