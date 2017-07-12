package com.invariant.devices.posiflex.service.check;

import java.util.List;

/**
 * Чек
 */
public interface Check {

    CheckHeader getHeader();

    List<CheckRow> getCheckRows();
}
