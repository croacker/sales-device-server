package com.invariant.rs.service;

import java.io.UnsupportedEncodingException;

/**
 *
 */
public class DataService {

    /**
     * В отсутствие DI, инстанс
     */
    private static DataService instance;

    public static DataService getInstance() {
        if (instance == null) {
            instance = new DataService();
        }
        return instance;
    }

    public byte[] toBytes(char[] chars) {
        String command = new String(chars);
        return toBytes(command);
    }

    public byte[] toBytes(String command) {
        try {
            return command.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
