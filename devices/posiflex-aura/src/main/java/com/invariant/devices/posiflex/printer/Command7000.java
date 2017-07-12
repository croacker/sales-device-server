package com.invariant.devices.posiflex.printer;

/**
 * Команды для Posiflex aura 7000
 */
public class Command7000 {

    // Escape (ESC)
    public static final byte[] ESC = new byte[] { 27 };

    // Line feed (LF)
    public static final byte[] LF = new byte[] { 10 };

    // Инициализация принтера
    public static final byte[] INIT = new byte[] { 27, 64 };

    // Установить ANSI кодировку CP1251
    public static final byte[] CHARSET_RUS = new byte[] { 27, 116, 5 };

    // Отрезать
    public static final byte[] CUT = new byte[] { 27, 105 };

    // Установить шрифт, следущий символ номер шрифта
    public static final byte[] FONT = new byte[] { 27, 33 };

    // FONT 1
    public static final byte[] FONT1 = new byte[] { 27, 105, 0 };

    // FONT 1 Underline
    public static final byte[] FONT1U = new byte[] { 27, 105, -62, -128 };

    // FONT 1 Bold
    public static final byte[] FONT1B = new byte[] { 27, 105, 8 };

    // FONT 1 Bold Underline
    public static final byte[] FONT1BU = new byte[] { 27, 105, -62, -120 };

    // FONT 2
    public static final byte[] FONT2 = new byte[] { 27, 105, 16 };

    // FONT 2 Underline
    public static final byte[] FONT2U = new byte[] { 27, 105, -62, -112 };

    // FONT 2 Bold
    public static final byte[] FONT2B = new byte[] { 27, 105, 24 };

    // FONT 2 Bold Underline
    public static final byte[] FONT2BU = new byte[] { 27, 105, -62, -104 };

    // FONT 3
    public static final byte[] FONT3 = new byte[] { 27, 105, 32 };

    public static final byte[] FONT31 = new byte[] { 27, -62, -96 };

    // FONT 3 Underline
    public static final byte[] FONT3U = new byte[] { 27, 105, -62, -96 };

    // FONT 3 Bold
    public static final byte[] FONT3B = new byte[] { 27, 105, 40 };

    // FONT 3 Bold Underline
    public static final byte[] FONT3BU = new byte[] { 27, 105, -62, -88 };

    // FONT 4
    public static final byte[] FONT4 = new byte[] { 27, 105, 48 };

    // FONT 4 Underline
    public static final byte[] FONT4U = new byte[] { 27, 105, -62, -80 };

    // FONT 4 Bold
    public static final byte[] FONT4B = new byte[] { 27, 105, 56 };

    // FONT 4 Bold Underline
    public static final byte[] FONT4BU = new byte[] { 27, 105, -62, -72 };

    // Select device 1B 3D 01, init 1B 40, final: 1D 61 0F
    public static final byte[] SELECT_INIT_FINAL = new byte[] { 27, 61, 1, 27, 64, 29, 97, 15 };

    // 1B 74 05, 1B 52 00 - Set Russian character code table
    public static final byte[] SET_RUSSIAN_CODE_TABLE = new byte[] { 27, 116, 5, 27, 82, 0 };

}
