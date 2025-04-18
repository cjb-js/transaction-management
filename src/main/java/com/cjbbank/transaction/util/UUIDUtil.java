package com.cjbbank.transaction.util;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;

public class UUIDUtil {

    public static String generateUUID() {

        return StringUtils.remove(UUID.randomUUID().toString(), '-');
    }
}
