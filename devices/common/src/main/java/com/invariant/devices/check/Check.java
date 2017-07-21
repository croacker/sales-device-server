package com.invariant.devices.check;

import java.util.List;

public interface Check {

    CheckHeader getHeader();

    List<CheckRow> getRows();

    CheckSummary getSummary();

}
