package com.invariant.devices.posiflex.printer;

/**
 * Команды для Posiflex aura 6800/6900
 */
public class Command6800 {

    /**
     * Escape (ESC)
     */
    public static final byte[] ESC = new byte[] { 27 };

    /**
     * Line feed (LF)
     */
    public static final byte[] LF = new byte[] { 10 };

    /**
     * Отрезать
     */
    public static final byte[] CUT = new byte[] { 27, 105 };

    /**
     * Микро шрифт 8*16
     */
    public static final byte[] FONT0 = new byte[] { 27, 77, 1 };

    /**
     * Малый шрифт 12*24
     */
    public static final byte[] FONT1 = new byte[] { 27, 32, 0 };

    /**
     * Крупный шрифт 12*24, с двойной высотой
     */
    public static final byte[] FONT4 = new byte[] { 27, 33, 32 };// == FONT_DOUBLE_WIDHT;

    /**
     * Третий параметр увеличивает ширина отступа между буквами
     */
    public static final byte[] FONT1_2 = new byte[] { 27, 32, 1 };

    /**
     * Стандартный шрифт 12*24
     */
    public static final byte[] FONT_NORMAL_1224 = new byte[] { 27, 33, 0 };

    /**
     * Стандартный шрифт 8*16
     */
    public static final byte[] FONT_NORMAL_816 = new byte[] { 27, 33, 1 };

    /**
     * Жирный шрифт
     */
    public static final byte[] FONT_BOLD = new byte[] { 27, 33, 8 };

    /**
     * Шрифт с подчеркиванием
     */
    public static final byte[] FONT_UNDERLINE = new byte[] { 27, 33, -128 };

    /**
     * Двойная высота
     */
    public static final byte[] FONT_DOUBLE_HEIGHT = new byte[] { 27, 33, 16 };

    /**
     * Двойная высота
     */
    public static final byte[] FONT_DOUBLE_WIDHT = new byte[] { 27, 33, 32 };

}
