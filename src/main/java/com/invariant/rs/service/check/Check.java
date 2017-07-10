package com.invariant.rs.service.check;

import java.util.List;

/**
 * Чек
 */
public interface Check {

    CheckHeader getHeader();

    List<CheckRow> getCheckRows();
}
