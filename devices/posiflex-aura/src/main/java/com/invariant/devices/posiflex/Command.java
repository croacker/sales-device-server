package com.invariant.devices.posiflex;

/**
 *
 */
public class Command {

    /**
     * //TODO:Команды от 7000, требуется ревизия
     */
    public static class CommandsByte7000 {
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

    /**
     * Команды для 6800/6900 определенные методом экспериментов
     */
    public static class CommandsByte6800 {
        /**
         * Escape (ESC)
         */
        public static final byte[] ESC = new byte[]{27};
        /**
         * Line feed (LF)
         */
        public static final byte[] LF = new byte[]{10};
        /**
         * Отрезать
         */
        public static final byte[] CUT = new byte[]{27, 105};
        /**
         * Микро шрифт 8*16
         */
        public static final byte[] FONT0 = new byte[]{27, 77, 1};
        /**
         * Малый шрифт 12*24
         */
        public static final byte[] FONT1 = new byte[]{27, 32, 0};
        /**
         * Крупный шрифт 12*24, с двойной высотой
         */
        public static final byte[] FONT4 = new byte[]{27, 33, 32};//FONT_DOUBLE_WIDHT;
        /**
         * Третий параметр увеличивает ширина отступа между буквами
         */
        public static final byte[] FONT1_2 = new byte[]{27, 32, 1};
        /**
         * Стандартный шрифт 12*24
         */
        public static final byte[] FONT_NORMAL_1224 = new byte[]{27, 33, 0};
        /**
         * Стандартный шрифт 8*16
         */
        public static final byte[] FONT_NORMAL_816 = new byte[]{27, 33, 1};
        /**
         * Жирный шрифт
         */
        public static final byte[] FONT_BOLD = new byte[]{27, 33, 8};
        /**
         * Шрифт с подчеркиванием
         */
        public static final byte[] FONT_UNDERLINE = new byte[]{27, 33, -128};
        /**
         * Двойная высота
         */
        public static final byte[] FONT_DOUBLE_HEIGHT = new byte[]{27, 33, 16};
        /**
         * Двойная высота
         */
        public static final byte[] FONT_DOUBLE_WIDHT = new byte[]{27, 33, 32};

    }

}
