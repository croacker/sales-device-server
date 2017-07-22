package com.invariant.devices.posiflex.service;

/**
 *
 */
public class ConverterService {

    /**
     * В отсутствие DI, инстанс
     */
    private static ConverterService instance;

    public static ConverterService getInstance() {
        if (instance == null) {
            instance = new ConverterService();
        }
        return instance;
    }

}
