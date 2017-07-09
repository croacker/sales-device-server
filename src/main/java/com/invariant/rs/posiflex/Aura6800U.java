package com.invariant.rs.posiflex;

import com.invariant.rs.service.check.Check;
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

    public void print(Check check){
        printHeader(check.getHeader());
        printRows(check.getCheckRow());
        printSummary(check.getSummary());
    }

    private void printHeader(List<String> header){
        for (String str: header){
            setFont4();
            writeStringCP866(str);
        }
    }

    private void printRows(List<CheckRow> rows){
        for (CheckRow row: rows){
            printRow(row);
        }
    }

    private void printRow(CheckRow row) {
        writeStringCP866(row.getName());
        writeStringCP866(row.getCost());
        writeStringCP866(row.getCount());
    }

    private void printSummary(List<String> summary){
        for (String str: summary){
            writeStringCP866(str);
        }
    }
}