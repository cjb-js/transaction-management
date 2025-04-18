package com.cjbbank.transaction.api.model;

import lombok.Data;

@Data
public class PagedData {
    private int count;
    private Object rows;
}
