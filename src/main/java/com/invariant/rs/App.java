package com.invariant.rs;

import java.io.UnsupportedEncodingException;

import com.invariant.rs.posiflex.Aura6800U;
import com.invariant.rs.service.SerialPortService;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 *
 */
public class App {

    public static void main(String[] args) {
        new App().start();
    }

    private void start(){
        SerialPort serialPort = getSerialPort();
        SerialPortList.getPortNames();
        try {
            writeTest(serialPort);
            serialPort.closePort();
        } catch (SerialPortException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private SerialPortMock getSerialPortStub() {
        return new SerialPortMock();
    }

    private void writeTest(SerialPort serialPort) throws SerialPortException {
//        byte[] command = toBytes(Aura6800U.Commands.SELECT_INIT_FINAL);//Команда меняет шрифт на более толстый
//        serialPort.writeBytes(command);

//        command = toBytes(Aura6800U.Commands.SET_RUSSIAN_CODE_TABLE);
//        serialPort.writeBytes(command);

        byte[] command = toBytes(
//                new String(Aura6800U.Commands.FONT4BU) +
                "Pos & Flex Print Test");
        serialPort.writeBytes(command);

        command = toBytes(Aura6800U.Commands.LF);
        serialPort.writeBytes(command);

        command = toBytes(
//                new String(Aura6800U.Commands.FONT1) +
                        new String(Aura6800U.Commands.LF)
        );
        serialPort.writeBytes(command);

        command = toBytes(
                //new String(Aura6800U.Commands.FONT1) +
                "GOOD 1.........................[42 usd.]"
                + new String(Aura6800U.Commands.LF)
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
                + new String(Aura6800U.Commands.LF)
        );
//        serialPort.writeBytes(command);
        serialPort.writeString("GOOD 2.........................[42 usd.]");
        writeLf(serialPort);

        command = toBytes(new String(Aura6800U.Commands.FONT31));
        serialPort.writeBytes(command);

        serialPort.writeString("GOOD 3....[21 usd.]");

        command = toBytes(
//                new String(Aura6800U.Commands.FONT4) +
                        "GOOD 4....[21 usd.]"
                + new String(Aura6800U.Commands.LF)
        );
//        serialPort.writeBytes(command);
        command = toBytes(new String(Aura6800U.Commands.ESC));
        writeLf(serialPort);

        serialPort.writeBytes(command);
        serialPort.writeString("GOOD 4....[21 usd.]");
        writeLf(serialPort);

        for(int i = 0;i < 2; i++) {
            writeLf(serialPort);
        }

        command = toBytes(Aura6800U.Commands.CUT);
        serialPort.writeBytes(command);

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
        return SerialPortService.getInstance().getSerialPort();
    }

    private void writeLf(SerialPort serialPort) throws SerialPortException {
        byte[] command = toBytes(Aura6800U.Commands.LF);
        serialPort.writeBytes(command);
    }


    private byte[] toBytes(char[] chars) {
        String command = new String(chars);
        return toBytes(command);
    }

    private byte[] toBytes(String command) {
        try {
            return command.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private class SerialPortMock {
        public void writeBytes(byte[] command) {

        }
    }
}
