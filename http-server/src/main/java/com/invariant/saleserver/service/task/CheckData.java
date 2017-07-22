package com.invariant.saleserver.service.task;

import com.invariant.devices.posiflex.service.check.AuraCheck;
import com.invariant.devices.posiflex.service.check.Device;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
public class CheckData {

    @Getter
    @Setter
    private Device device;

    @Getter
    @Setter
    private AuraCheck check;

}
