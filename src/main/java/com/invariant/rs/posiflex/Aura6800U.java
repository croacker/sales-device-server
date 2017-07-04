package com.invariant.rs.posiflex;

/**
 *
 */
public class Aura6800U {

    public static class Commands{
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

}
