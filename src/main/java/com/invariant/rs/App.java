package com.invariant.rs;

import java.io.UnsupportedEncodingException;

import com.invariant.rs.posiflex.Aura6800U;
import com.invariant.rs.service.SerialPortService;

import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 */
public class App {

    public static void main(String[] args) {
        new App().start();
    }

    private void start(){
        SerialPort serialPort = getSerialPort();
        try {
            writeTest(serialPort);
        } catch (SerialPortException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private SerialPortMock getSerialPortStub() {
        return new SerialPortMock();
    }

    private void writeTest(SerialPort serialPort) throws SerialPortException {
        byte[] command = toBytes(Aura6800U.Commands.SELECT_INIT_FINAL);
        serialPort.writeBytes(command);

        command = toBytes(Aura6800U.Commands.SET_RUSSIAN_CODE_TABLE);
        serialPort.writeBytes(command);

        command = toBytes(new String(Aura6800U.Commands.FONT4BU) + "Тест устройства");
        serialPort.writeBytes(command);

        command = toBytes(Aura6800U.Commands.LF);
        serialPort.writeBytes(command);

        command = toBytes(new String(Aura6800U.Commands.FONT1) + new String(Aura6800U.Commands.LF));
        serialPort.writeBytes(command);

        command = toBytes(new String(Aura6800U.Commands.FONT1)
                + "Font 1.........................[42 symbol]"
                + new String(Aura6800U.Commands.LF));
        serialPort.writeBytes(command);

        command = toBytes(new String(Aura6800U.Commands.FONT2)
                + "Font 2.........................[42 symbol]"
                + new String(Aura6800U.Commands.LF));
        serialPort.writeBytes(command);

        command = toBytes(new String(Aura6800U.Commands.FONT3)
                + "Font 3....[21 symbol]"
                + new String(Aura6800U.Commands.LF));
        serialPort.writeBytes(command);

        command = toBytes(new String(Aura6800U.Commands.FONT4)
                + "Font 4....[21 symbol]"
                + new String(Aura6800U.Commands.LF));
        serialPort.writeBytes(command);

        command = toBytes(Aura6800U.Commands.LF);
        serialPort.writeBytes(command);

        command = toBytes(Aura6800U.Commands.LF);
        serialPort.writeBytes(command);

        command = toBytes(Aura6800U.Commands.CUT);
        serialPort.writeBytes(command);
    }

    private SerialPort getSerialPort(){
        //dmesg | grep tty
        ///dev/ttyUSB0
        return SerialPortService.getInstance().getSerialPort();
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
