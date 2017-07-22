package com.invariant.devices.service.serial;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 *
 */
public class DataAccumulator extends ByteArrayOutputStream {

    private PrintStream printStream;

    public String getData(){
        return new String(toByteArray(), StandardCharsets.UTF_8);
    }

    public PrintStream getPrintStream() {
        return printStream;
    }

    public void setPrintStream(PrintStream value) {
        printStream = value;
    }
}
