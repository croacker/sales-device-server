package com.invariant.rs.posiflex;

import com.invariant.rs.printer.Printer;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 */
public class CommonPrinter implements Printer {

    /**
     * //TODO:Команды от 7000, требуется ревизия
     */
    public static class CommandsChar {
        // Escape (ESC)
        public static final char[] ESC = new char[]{27};
        // Line feed (LF)
        public static final char[] LF = new char[]{10};
        // Инициализация принтера
        public static final char[] INIT = new char[]{27, 64};
        // Установить ANSI кодировку CP1251
        public static final char[] CHARSET_RUS = new char[]{27, 116, 5};
        // Отрезать
        public static final char[] CUT = new char[]{27, 105};

        // Установить шрифт, следущий символ номер шрифта
        public static final char[] FONT = new char[]{27, 33};
        // FONT 1
        public static final char[] FONT1 = new char[]{27, 105, 0};
        // FONT 1 Underline
        public static final char[] FONT1U = new char[]{27, 105, 128};
        // FONT 1 Bold
        public static final char[] FONT1B = new char[]{27, 105, 8};
        // FONT 1 Bold Underline
        public static final char[] FONT1BU = new char[]{27, 105, 136};

        // FONT 2
        public static final char[] FONT2 = new char[]{27, 105, 16};
        // FONT 2 Underline
        public static final char[] FONT2U = new char[]{27, 105, 144};
        // FONT 2 Bold
        public static final char[] FONT2B = new char[]{27, 105, 24};
        // FONT 2 Bold Underline
        public static final char[] FONT2BU = new char[]{27, 105, 152};

        // FONT 3
        public static final char[] FONT3 = new char[]{27, 105, 32};
        public static final char[] FONT31 = new char[]{27, 160};

        // FONT 3 Underline
        public static final char[] FONT3U = new char[]{27, 105, 160};
        // FONT 3 Bold
        public static final char[] FONT3B = new char[]{27, 105, 40};
        // FONT 3 Bold Underline
        public static final char[] FONT3BU = new char[]{27, 105, 168};

        // FONT 4
        public static final char[] FONT4 = new char[]{27, 105, 48};
        // FONT 4 Underline
        public static final char[] FONT4U = new char[]{27, 105, 176};
        // FONT 4 Bold
        public static final char[] FONT4B = new char[]{27, 105, 56};
        // FONT 4 Bold Underline
        public static final char[] FONT4BU = new char[]{27, 105, 184};

        //Select device 1B 3D 01, init 1B 40, final: 1D 61 0F
        public static final char[] SELECT_INIT_FINAL = new char[]{27, 61, 1, 27, 64, 29, 97, 15};

        // 1B 74 05, 1B 52 00 - Set Russian character code table
        public static final char[] SET_RUSSIAN_CODE_TABLE = new char[]{27, 116, 5, 27, 82, 0};
    }

    /**
     * //TODO:Команды от 7000, требуется ревизия
     */
    public static class CommandsByte {
        // Escape (ESC)
        public static final byte[] ESC = new byte[]{27};
        // Line feed (LF)
        public static final byte[] LF = new byte[]{10};
        // Инициализация принтера
        public static final byte[] INIT = new byte[]{27, 64};
        // Установить ANSI кодировку CP1251
        public static final byte[] CHARSET_RUS = new byte[]{27, 116, 5};
        // Отрезать
        public static final byte[] CUT = new byte[]{27, 105};

        // Установить шрифт, следущий символ номер шрифта
        public static final byte[] FONT = new byte[]{27, 33};
        // FONT 1
        public static final byte[] FONT1 = new byte[]{27, 105, 0};
        // FONT 1 Underline
        public static final byte[] FONT1U = new byte[]{27, 105, -62, -128};
        // FONT 1 Bold
        public static final byte[] FONT1B = new byte[]{27, 105, 8};
        // FONT 1 Bold Underline
        public static final byte[] FONT1BU = new byte[]{27, 105, -62, -120};

        // FONT 2
        public static final byte[] FONT2 = new byte[]{27, 105, 16};
        // FONT 2 Underline
        public static final byte[] FONT2U = new byte[]{27, 105, -62, -112};
        // FONT 2 Bold
        public static final byte[] FONT2B = new byte[]{27, 105, 24};
        // FONT 2 Bold Underline
        public static final byte[] FONT2BU = new byte[]{27, 105, -62, -104};

        // FONT 3
        public static final byte[] FONT3 = new byte[]{27, 105, 32};
        public static final byte[] FONT31 = new byte[]{27, -62, -96};

        // FONT 3 Underline
        public static final byte[] FONT3U = new byte[]{27, 105, -62, -96};
        // FONT 3 Bold
        public static final byte[] FONT3B = new byte[]{27, 105, 40};
        // FONT 3 Bold Underline
        public static final byte[] FONT3BU = new byte[]{27, 105, -62, -88};

        // FONT 4
        public static final byte[] FONT4 = new byte[]{27, 105, 48};
        // FONT 4 Underline
        public static final byte[] FONT4U = new byte[]{27, 105, -62, -80};
        // FONT 4 Bold
        public static final byte[] FONT4B = new byte[]{27, 105, 56};
        // FONT 4 Bold Underline
        public static final byte[] FONT4BU = new byte[]{27, 105, -62, -72};

        //Select device 1B 3D 01, init 1B 40, final: 1D 61 0F
        public static final byte[] SELECT_INIT_FINAL = new byte[]{27, 61, 1, 27, 64, 29, 97, 15};

        // 1B 74 05, 1B 52 00 - Set Russian character code table
        public static final byte[] SET_RUSSIAN_CODE_TABLE = new byte[]{27, 116, 5, 27, 82, 0};
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
     * Line feed
     *
     * @throws SerialPortException
     */
    private void lf() throws SerialPortException {
        serialPort.writeBytes(CommandsByte.LF);
    }

    /**
     * Escape
     *
     * @throws SerialPortException
     */
    private void esc() throws SerialPortException {
        serialPort.writeBytes(CommandsByte.ESC);
    }

    public void writeBytes(byte[] bytes) throws SerialPortException {
        serialPort.writeBytes(bytes);
    }

    public void writeString(String str) throws SerialPortException {
        serialPort.writeString(str);
    }
}
