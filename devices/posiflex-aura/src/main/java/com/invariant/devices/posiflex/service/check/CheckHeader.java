package com.invariant.devices.posiflex.service.check;

/**
 *
 */
public interface CheckHeader {

    String getOrderNumber();

    String getPosNumber();

    String getCheckNumber();

    String getDateTime();

    String getWaiter();

    String getTable();
}
