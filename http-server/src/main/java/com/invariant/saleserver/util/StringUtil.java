package com.invariant.saleserver.util;

import com.google.common.base.Strings;

/**
 *
 */
public class StringUtil {

    public static final String EMPTY = "";

    public static boolean isEmpty(String str){
        return Strings.isNullOrEmpty(str);
    }

}
