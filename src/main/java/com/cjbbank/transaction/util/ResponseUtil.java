package com.cjbbank.transaction.util;

import java.util.Collections;

import com.cjbbank.transaction.api.model.PagedData;

public class ResponseUtil {

    public static PagedData wrapPagedData(int totalSize, Object data) {

        PagedData gpr = new PagedData();
        gpr.setRows(data);
        gpr.setCount(totalSize);

        return gpr;
    }

    public static PagedData emptyPagedData() {

        PagedData gpr = new PagedData();
        gpr.setRows(Collections.emptyList());

        gpr.setCount(0);

        return gpr;
    }

}
