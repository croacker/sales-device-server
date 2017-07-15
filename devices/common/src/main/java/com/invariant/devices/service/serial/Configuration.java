package com.invariant.devices.service.serial;

/**
 * 15.07.2017.
 */
public interface Configuration {

    String getName();

    int getBaudRate();

    int getDataBits();

    int getstopBits();

    int getParity();


}
