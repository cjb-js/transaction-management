package com.cjbbank.transaction.enums;

import org.springframework.http.HttpStatus;

public enum TransactionReturnCodeEnum {

    TRANSACTION_NOT_FOUND("transaction-0001", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "transaction not found with id {0}");

    public final ReturnCodeHolder VALUE;

    TransactionReturnCodeEnum(String code, ReturnCodeTypeEnum type, HttpStatus httpStatus, String errorMsg) {

        VALUE = new ReturnCodeHolder(code, httpStatus, errorMsg, type);

    }

}
