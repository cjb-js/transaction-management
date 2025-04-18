package com.cjbbank.transaction.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.cjbbank.transaction.api.model.GeneralResponse;
import com.cjbbank.transaction.api.model.Message;
import com.cjbbank.transaction.enums.CommonReturnCodeEnum;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<GeneralResponse<Object>> handleBaseException(GeneralException e, HttpServletRequest req) {

        GeneralResponse<Object> genericResponse = GeneralResponse.builder().code(e.getErrorCode().getCode())
                .msg(Message.of(e.getErrorCode().getErrorMsg(), e.getParams())).build();

        log.error("handleBaseException", e);
        return ResponseEntity.status(e.getErrorCode().getHttpStatus()).body(genericResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        GeneralResponse<Object> genericResponse = GeneralResponse.builder()
                .code(CommonReturnCodeEnum.ARGUMENTS_INVALID.VALUE.getCode())
                .msg(Message.of(getBindingResultMessage(ex.getBindingResult()))).build();

        log.error("handleArgumentNotValidException", ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    }

    // default exception handler to handle not captured exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse<Object>> handleException(Exception e, HttpServletRequest req) {

        GeneralResponse<Object> genericResponse = GeneralResponse.builder()
                .code(CommonReturnCodeEnum.SYSTEM_ERROR.VALUE.getCode())
                .msg(Message.of(CommonReturnCodeEnum.SYSTEM_ERROR.VALUE.getErrorMsg(), e.getMessage())).build();

        log.error("exception is forgot to be captured", e);
        return ResponseEntity.status(CommonReturnCodeEnum.SYSTEM_ERROR.VALUE.getHttpStatus()).body(genericResponse);
    }

    // Convert the BindingResult to a human-readable string without exposing any server info
    // Begin with: "Invalid arguments: \n"
    // Each error message is in the format of "field: message"
    // Each error starts a new line
    private String getBindingResultMessage(BindingResult bindingResult) {
        return "Invalid arguments: \n" + bindingResult.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("\n"));
    }

}