package com.invariant.devices.posiflex.service.check;

import lombok.Getter;
import lombok.Setter;

/**
 *
 */
public class AuraCheckRow implements CheckRow {

    @Getter @Setter
    String guest;

    @Getter @Setter
    String goodName;

    @Getter @Setter
    String count;

}
