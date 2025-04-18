package com.cjbbank.transaction.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeneralResponse<T> {
    private String code;
    private T data;
    private Message msg;

}
