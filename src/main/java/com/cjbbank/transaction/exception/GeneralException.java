package com.cjbbank.transaction.exception;

import com.cjbbank.transaction.enums.ReturnCodeHolder;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GeneralException extends Exception {

    protected Object[] params;

    private static final long serialVersionUID = -5288261997968208671L;

    protected ReturnCodeHolder errorCode;

    public GeneralException(ReturnCodeHolder errorCode, Object... params) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
        this.params = params;
    }

    public GeneralException(ReturnCodeHolder errorCode, Throwable cause, Object... params) {
        super(errorCode.getErrorMsg(), cause);
        this.errorCode = errorCode;
        this.params = params;
    }

    public GeneralException(ReturnCodeHolder errorCode, String message, Throwable cause, Object... params) {
        super(message, cause);
        this.errorCode = errorCode;
        this.params = params;
    }
}
