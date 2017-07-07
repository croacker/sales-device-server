package com.invariant.rs;

import java.io.UnsupportedEncodingException;

import com.invariant.rs.posiflex.Aura6800U;
import com.invariant.rs.posiflex.Command;
import com.invariant.rs.service.DataService;
import com.invariant.rs.service.SerialPortService;

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
            writeTest1(printer);
            serialPort.closePort();
        } catch (SerialPortException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private SerialPortMock getSerialPortStub() {
        return new SerialPortMock();
    }

    private void writeTest1(Aura6800U printer) throws SerialPortException {

        printer.cut();
    }

    //TODO:Образец
    private void writeTest0(SerialPort serialPort) throws SerialPortException {
        byte[] command;
        //27, 33 - команда
        //0 - расстояние между буквами
        //0 - отступ
        serialPort.writeBytes(new byte[]{27, 32, 0}); //Самый большой шрифт

        serialPort.writeBytes(getBytesCP866("Товар А"));
        writeLf(serialPort);

        serialPort.writeBytes(new byte[]{27, 32, 0});
        serialPort.writeBytes(getBytesCP866("Товар Б"));
        writeLf(serialPort);

        serialPort.writeBytes(new byte[]{27, 32, 0});
        serialPort.writeBytes(getBytesCP866("Товар В"));
        writeLf(serialPort);

        serialPort.writeBytes(new byte[]{27, 32, 0});
        serialPort.writeBytes(getBytesCP866("Товар Е"));
        writeLf(serialPort);

        if(true){
            return;
        }

        writeLf(serialPort);

        command = toBytes(
                //new String(Aura6800U.Commands.FONT1) +
                "GOOD 1.........................[42 usd.]"
                + new String(Command.CommandsByte6800.LF)
        );
//        serialPort.writeBytes(command);
        try {
//            serialPort.writeString("Товар 1.....[42 usd.]", "CP866");
            serialPort.writeString(new String("Товар 1.....[42 usd.]"
                    .getBytes("UTF-8"), "windows-1251"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writeLf(serialPort);

        command = toBytes(
//                new String(Aura6800U.Commands.FONT2) +
                        "GOOD 2.........................[42 usd.]"
                + new String(Command.CommandsByte6800.LF)
        );
//        serialPort.writeBytes(command);
        serialPort.writeString("GOOD 2.........................[42 usd.]");
        writeLf(serialPort);

        serialPort.writeString("GOOD 3....[21 usd.]");

        command = toBytes(
//                new String(Aura6800U.Commands.FONT4) +
                        "GOOD 4....[21 usd.]"
                + new String(Command.CommandsByte6800.LF)
        );
//        serialPort.writeBytes(command);
        command = toBytes(new String(Command.CommandsByte6800.ESC));
        writeLf(serialPort);

        serialPort.writeBytes(command);
        serialPort.writeString("GOOD 4....[21 usd.]");
        writeLf(serialPort);

        for(int i = 0;i < 2; i++) {
            writeLf(serialPort);
        }

        cut(serialPort);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ОК
     * @return
     */
    private SerialPort getSerialPort(){
        //dmesg | grep tty
        ///dev/ttyUSB0
        return SerialPortService.getInstance().getPort();
    }

    private void writeLf(SerialPort serialPort) throws SerialPortException {
        serialPort.writeBytes(Command.CommandsByte6800.LF);
    }

    private void cut(SerialPort serialPort) throws SerialPortException {
        serialPort.writeBytes(Command.CommandsByte6800.CUT);
    }

    private byte[] toBytes(String str) {
        return getDataService().toBytes(str);
    }

    private byte[] getBytesCP866(String str) {
        return getDataService().getBytesCP866(str);
    }

    private class SerialPortMock {
        public void writeBytes(byte[] command) {

        }
    }
}
