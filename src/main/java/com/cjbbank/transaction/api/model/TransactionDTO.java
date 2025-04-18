package com.cjbbank.transaction.api.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class TransactionDTO {
    private String id;

    private String name;

    private BigDecimal amount;

    private String createdBy;

    private String updatedBy;

    private String createdTime;

    private String updatedTime;

    public TransactionDTO id(String id) {
        this.id = id;
        return this;
    }
}
