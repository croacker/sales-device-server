package com.invariant.rs.service.check;

import java.util.List;

/**
 * Чек
 */
public interface Check {

    List<String> getHeader();

    List<CheckRow> getCheckRow();

    List<String> getSummary();

}
