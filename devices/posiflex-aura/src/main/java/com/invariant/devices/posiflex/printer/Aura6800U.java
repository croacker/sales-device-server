package com.invariant.devices.posiflex.printer;

import com.invariant.devices.check.Check;
import com.invariant.devices.check.CheckHeader;
import com.invariant.devices.check.CheckRow;
import com.invariant.devices.posiflex.service.check.AuraCheck;
import com.invariant.devices.posiflex.service.check.AuraCheckRow;
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

    public void print(AuraCheck check) {
        lf();
        printHeader(check.getHeader());
        printRows(check.getRows());
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

    private void printRows(List<AuraCheckRow> rows) {
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