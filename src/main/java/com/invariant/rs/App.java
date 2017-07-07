package com.invariant.rs;

import java.io.UnsupportedEncodingException;

import com.invariant.rs.posiflex.Aura6800U;
import com.invariant.rs.posiflex.CommonPrinter;
import com.invariant.rs.service.SerialPortService;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * dev.0.0.1
 */
public class App {

    public static final String CP866 = "CP866";

    public static void main(String[] args) {
        new App().start();
    }

    private void start(){
        SerialPort serialPort = getSerialPort();
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

        byte[] command = getBytesCP866("ааа");
//                new String(Aura6800U.Commands.FONT4BU) +
//                "Pos & Flex Print Test");
        //27, 33 - команда
        //0 - расстояние между буквами
        //0 - отступ
        serialPort.writeBytes(new byte[]{27, 32, 0});

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

        command = toBytes(
//                new String(Aura6800U.Commands.FONT1) +
                        new String(Aura6800U.CommandsChar.LF)
        );
        serialPort.writeBytes(command);

        command = toBytes(
                //new String(Aura6800U.Commands.FONT1) +
                "GOOD 1.........................[42 usd.]"
                + new String(Aura6800U.CommandsChar.LF)
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
                + new String(Aura6800U.CommandsChar.LF)
        );
//        serialPort.writeBytes(command);
        serialPort.writeString("GOOD 2.........................[42 usd.]");
        writeLf(serialPort);

        command = toBytes(new String(Aura6800U.CommandsChar.FONT31));
        serialPort.writeBytes(command);

        serialPort.writeString("GOOD 3....[21 usd.]");

        command = toBytes(
//                new String(Aura6800U.Commands.FONT4) +
                        "GOOD 4....[21 usd.]"
                + new String(Aura6800U.CommandsChar.LF)
        );
//        serialPort.writeBytes(command);
        command = toBytes(new String(Aura6800U.CommandsChar.ESC));
        writeLf(serialPort);

        serialPort.writeBytes(command);
        serialPort.writeString("GOOD 4....[21 usd.]");
        writeLf(serialPort);

        for(int i = 0;i < 2; i++) {
            writeLf(serialPort);
        }

        command = toBytes(Aura6800U.CommandsChar.CUT);
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
        return SerialPortService.getInstance().getPort();
    }

    private void writeLf(SerialPort serialPort) throws SerialPortException {
        serialPort.writeBytes(CommonPrinter.CommandsByte.LF);
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

    private byte[] getBytesCP866(String str){
        try {
            return str.getBytes(CP866);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
