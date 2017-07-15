package com.invariant.devices.posiflex;

import com.invariant.devices.posiflex.printer.Aura6800U;
import com.invariant.devices.posiflex.service.DataService;
import com.invariant.devices.service.SerialPortService;
import com.invariant.devices.posiflex.service.check.AuraCheck;
import com.invariant.devices.posiflex.service.check.AuraCheckHeader;
import com.invariant.devices.posiflex.service.check.AuraCheckRow;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 * dev.0.0.1
 */
public class App {

    private DataService getDataService(){
        return DataService.getInstance();
    }

    public static void main(String[] args) {
        new App().start();
    }

    private void start(){
        SerialPort serialPort = getSerialPort();
        try {
            Aura6800U printer = new Aura6800U(serialPort);
            writeTestCheck(printer);
            serialPort.closePort();
        } catch (SerialPortException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private SerialPortMock getSerialPortStub() {
        return new SerialPortMock();
    }


    private void writeTestCheck(Aura6800U printer) throws SerialPortException {
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

        printer.print(check);
//        printer.printTest();
    }

    /**
     * ОК
     * @return
     */
    private SerialPort getSerialPort(){
        return SerialPortService.getInstance().getPort();
    }

    private class SerialPortMock {
        public void writeBytes(byte[] command) {

        }
    }
}
