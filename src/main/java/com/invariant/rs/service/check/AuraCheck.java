package com.invariant.rs.service.check;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AuraCheck implements Check {

    @Getter
    @Setter
    private CheckHeader header;

    @Getter @Setter
    private List<CheckRow> checkRows = new ArrayList<CheckRow>();

    public void addRow(AuraCheckRow row) {
        checkRows.add(row);
    }
}
