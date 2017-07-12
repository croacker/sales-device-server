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

    public static final int FONT_SMAL_LINE_COUNT  = 42;
    public static final int FONT_BIG_LINE_COUNT = 24;


    public Aura6800U(SerialPort serialPort) {
        super(serialPort);
    }

    public void printTest() {
        setFont1();
        writeString("123456789012345678901234567890123456789012");
        setFont4();
        writeString("123456789012345678901234");
        lf();
        lf();
        lf();
        cut();
    }

    public void print(Check check) {
        lf();
        printHeader(check.getHeader());
        printRows(check.getCheckRows());
        lf();
        lf();
        cut();
    }

    private void printHeader(CheckHeader header) {
        writeStringCP866("Заказ: " + header.getOrderNumber());
        lf();
        writeStringCP866("ПОС № " + header.getPosNumber() + "   ЧЕК № " + header.getCheckNumber());
        lf();
        writeStringCP866(header.getDateTime() + "   " + header.getWaiter());
        lf();
        setFont4();
        writeStringCP866("СТОЛИК " + header.getTable());
        lf();
        setFont1();
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
        writeString("------------------------------------------");
        lf();
        setFont4();
        writeStringCP866(row.getCount());
        writeString("   ");
        writeStringCP866(row.getGoodName());
        lf();
        setFont1();
    }

}