package com.invariant.devices.posiflex;

import com.invariant.devices.printer.Printer;
import com.invariant.devices.service.DataService;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 */
public class CommonPrinter implements Printer {

    private DataService getDataService() {
        return DataService.getInstance();
    }

    private SerialPort serialPort;

    public SerialPort getSerialPort() {
        return serialPort;
    }

    public void setSerialPort(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    public CommonPrinter(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    /**
     * Line feed & cariage return
     *
     * @throws SerialPortException
     */
    public void lf() {
        writeBytes(Command.CommandsByte6800.LF);
    }

    /**
     * Escape символ, управляющая последовательность
     *
     * @throws SerialPortException
     */
    public void esc() {
        writeBytes(Command.CommandsByte6800.ESC);
    }

    /**
     * Отрезать
     *
     * @throws SerialPortException
     */
    public void cut() {
        writeBytes(Command.CommandsByte6800.CUT);
    }

    /**
     * Установить в качестве текущего большой шрифт
     *
     * @throws SerialPortException
     */
    public void setFont1() {
        writeBytes(Command.CommandsByte6800.FONT_NORMAL_1224);
    }

    /**
     * Установить в качестве текущего самый малый шрифт
     *
     * @throws SerialPortException
     */
    public void setFont0() {
        writeBytes(Command.CommandsByte6800.FONT0);
    }

    /**
     * Установить в качестве текущего большой шрифт
     *
     * @throws SerialPortException
     */
    public void setFont4() {
        writeBytes(Command.CommandsByte6800.FONT4);
    }


    /**
     * Печать байт, либо передача управляющей последовательности
     *
     * @param bytes
     * @throws SerialPortException
     */
    public void writeBytes(byte[] bytes) {
        try {
            serialPort.writeBytes(bytes);
            sleep();
        } catch (SerialPortException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Печать строки, без перекодирования только для цифр и английских букв
     *
     * @param str
     * @throws SerialPortException
     */
    public void writeString(String str) {
        try {
            serialPort.writeString(str);
        } catch (SerialPortException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * Печать русских символов
     *
     * @param str
     * @throws SerialPortException
     */
    public void writeStringCP866(String str) {
        writeBytes(getDataService().getBytesCP866(str));
    }

    private void sleep(){
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
