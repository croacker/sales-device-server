package com.invariant.rs.posiflex;

import com.invariant.rs.service.check.Check;
import com.invariant.rs.service.check.CheckHeader;
import com.invariant.rs.service.check.CheckRow;
import jssc.SerialPort;

import java.util.List;

/**
 *
 */
public class Aura6800U extends CommonPrinter {

    public Aura6800U(SerialPort serialPort) {
        super(serialPort);
    }

    public void print(Check check) {
        printHeader(check.getHeader());
        printRows(check.getCheckRows());
        cut();
    }

    private void printHeader(CheckHeader header) {
        writeStringCP866("Заказ: " + header.getOrderNumber());
        lf();
        writeStringCP866("ПОС № " + header.getPosNumber() + "   ЧЕК № " + header.getCheckNumber());
        lf();
        writeStringCP866(header.getDateTime() + "   " + header.getWaiter());
        setFont4();
        writeStringCP866("СТОЛИК " + header.getTable());
        lf();
    }

    private void printRows(List<CheckRow> rows) {
        for (CheckRow row : rows) {
            printRow(row);
        }
    }

    private void printRow(CheckRow row) {
        if(row.getGuest() != null) {
            writeStringCP866(row.getGuest());
            lf();
        }
        writeString("------------------------------");
        lf();
        setFont4();
        writeStringCP866(row.getCount());
        writeStringCP866(row.getGoodName());
    }

}