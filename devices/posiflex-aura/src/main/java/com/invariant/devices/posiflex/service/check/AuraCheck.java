package com.invariant.devices.posiflex.service.check;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AuraCheck{

    @Getter
    @Setter
    private AuraCheckHeader header;

    @Getter @Setter
    private List<AuraCheckRow> rows = new ArrayList<>();

    @Getter @Setter
    private AuraCheckSummary summary;

    public void addRow(AuraCheckRow row) {
        rows.add(row);
    }
}
