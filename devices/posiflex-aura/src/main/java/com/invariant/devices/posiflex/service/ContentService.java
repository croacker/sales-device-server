package com.invariant.devices.posiflex.service;

import com.invariant.devices.posiflex.service.check.AuraCheck;
import com.invariant.devices.posiflex.service.check.AuraCheckHeader;
import com.invariant.devices.posiflex.service.check.AuraCheckRow;

public class ContentService {

    /**
     * В отсутствие DI, инстанс
     */
    private static ContentService instance;

    public static ContentService getInstance() {
        if (instance == null) {
            instance = new ContentService();
        }
        return instance;
    }

    public AuraCheck getTestCheck(){
        AuraCheck check = new AuraCheck();

        AuraCheckHeader header = new AuraCheckHeader();
        header.setOrderNumber("7_970");
        header.setPosNumber("1");
        header.setCheckNumber("77000972");
        header.setDateTime("28/06/2017 18:49:57");
        header.setWaiter("Сергей");
        header.setTable("3.5");
        check.setHeader(header);

        AuraCheckRow row = new AuraCheckRow();
        row.setGuest("ГОСТЬ 2");
        row.setCount("1");
        row.setGoodName("КАРТОФЕЛЬ ФРИ");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setCount("1");
        row.setGoodName("САЛАТ ФРАНЦУЗСКИЙ");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setGuest("ГОСТЬ 3");
        row.setCount("1");
        row.setGoodName("САЛАТ ПТИЧКА");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setGuest("ГОСТЬ 4");
        row.setCount("1");
        row.setGoodName("САЛАТ ФРАНЦУЗСКИЙ");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setGuest("ГОСТЬ 6");
        row.setCount("1");
        row.setGoodName("САЛАТ ГРЕЧЕСКИЙ");
        check.addRow(row);

        row = new AuraCheckRow();
        row.setCount("2");
        row.setGoodName("ПОЗЫ КЛАССИЧЕСКИЕ");
        check.addRow(row);

        return check;
    }

}
