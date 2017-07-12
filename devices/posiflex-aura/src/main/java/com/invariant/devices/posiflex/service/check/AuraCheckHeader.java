package com.invariant.devices.posiflex.service.check;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 */
@NoArgsConstructor
public class AuraCheckHeader implements CheckHeader {

    @Getter @Setter
    private String orderNumber;

    @Getter @Setter
    private String posNumber;

    @Getter @Setter
    private String checkNumber;

    @Getter @Setter
    private String dateTime;

    @Getter @Setter
    private String waiter;

    @Getter @Setter
    private String table;

}
