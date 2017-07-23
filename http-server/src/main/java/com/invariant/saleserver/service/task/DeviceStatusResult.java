package com.invariant.saleserver.service.task;

import com.invariant.devices.posiflex.printer.Printer;
import lombok.Getter;
import lombok.Setter;

public class DeviceStatusResult {

    @Getter
    @Setter
    private String status;

    @Getter
    @Setter
    private Printer printer;

}
