package com.cjbbank.transaction.enums;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnCodeHolder {

    private final String code;

    private final HttpStatus httpStatus;

    private final String errorMsg;

    private final ReturnCodeTypeEnum type;

}
