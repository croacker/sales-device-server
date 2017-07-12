package com.invariant.devices.posiflex.service;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.invariant.devices.posiflex.service.check.AuraCheck;
import com.invariant.devices.posiflex.service.check.AuraCheckHeader;
import com.invariant.devices.posiflex.service.check.AuraCheckRow;
import com.invariant.devices.posiflex.service.check.Check;

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

    public Check readFromFile() throws IOException {
        AuraCheck check;
        String fileName = "checkExample11.json";
        ObjectMapper mapper = new ObjectMapper();
        check = mapper.readValue(fileName, AuraCheck.class);
        return check;
    }

    public boolean writeToFile() throws IOException {
        AuraCheck check = new AuraCheck();
        AuraCheckHeader header = new AuraCheckHeader();
        header.setOrderNumber("1");
        check.setHeader(header);
        AuraCheckRow row = new AuraCheckRow();
        row.setGuest("Гость 1");
        row.setCount("1");
        row.setGoodName("КАРТОФЕЛЬ ФРИ");
        check.addRow(row);
        String fileName = "checkExample11.json";
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(fileName), check);
        return true;
    }
}
