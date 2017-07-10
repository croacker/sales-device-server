package com.invariant.rs.posiflex;

import com.invariant.rs.printer.Printer;
import com.invariant.rs.service.DataService;
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

}
