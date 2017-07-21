package com.invariant.devices.posiflex.service.check;

import com.invariant.devices.check.CheckSummary;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import com.invariant.devices.check.Check;
import com.invariant.devices.check.CheckRow;
import com.invariant.devices.check.CheckHeader;

/**
 *
 */
public class AuraCheck implements Check {

    @Getter
    @Setter
    private CheckHeader header;

    @Getter @Setter
    private List<CheckRow> rows = new ArrayList<CheckRow>();

    @Getter @Setter
    private CheckSummary summary;

    public void addRow(AuraCheckRow row) {
        rows.add(row);
    }
}
