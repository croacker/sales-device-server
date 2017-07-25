package com.invariant.saleserver.service.task;

import com.invariant.devices.posiflex.printer.Printer;

import java.util.concurrent.Callable;

/**
 *
 */
public class PrintCheckTask implements Callable<PrintCheckResult> {

    private final CheckData checkData;
    private final Printer printer;

    private String taskId;

    public void setTaskId(String value) {
        taskId = value;
    }

    public PrintCheckTask(CheckData checkData, Printer printer) {
        this.checkData = checkData;
        this.printer = printer;
    }

    @Override
    public PrintCheckResult call() throws Exception {
        PrintCheckResult result = new PrintCheckResult();
        printer.print(checkData.getCheck());
        result.setStatus("success");
        return result;
    }

}
