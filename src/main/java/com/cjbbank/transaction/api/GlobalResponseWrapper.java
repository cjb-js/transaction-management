package com.cjbbank.transaction.api;

import java.util.TreeMap;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.cjbbank.transaction.api.model.GeneralResponse;
import com.cjbbank.transaction.api.model.Message;
import com.cjbbank.transaction.enums.CommonReturnCodeEnum;

@ControllerAdvice
public class GlobalResponseWrapper implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        if (body instanceof String) {
            return body;
        }
        // avoid wrapping OpenAPI responses
        if (body instanceof TreeMap) {
            return body;
        }
        // avoid wrapping Swagger responses
        if (body instanceof byte[]) {
            return body;
        }
        // avoid wrapping GeneralResponse
        if (body instanceof GeneralResponse) {
            return body;
        }

        if (body instanceof ProblemDetail problemDetail) {
            StringBuilder message = new StringBuilder();
            //            message.append("Rejected Properties: \n");
            if (ObjectUtils.isNotEmpty(problemDetail.getProperties())) {
                problemDetail.getProperties().forEach((key, value) -> {
                    message.append(key).append(" : ").append(value).append(";");
                });
            }
            return GeneralResponse.builder().code(CommonReturnCodeEnum.ARGUMENTS_INVALID.VALUE.getCode())
                    .msg(Message.of(CommonReturnCodeEnum.ARGUMENTS_INVALID.VALUE.getErrorMsg(), message.toString()))
                    //                    .msg(Message.of(CommonReturnCodeEnum.ARGUMENTS_INVALID.VALUE.getErrorMsg(), problemDetail.toString()))
                    .data(null).build();
        }

        return GeneralResponse.builder().code(CommonReturnCodeEnum.SUCCESS.VALUE.getCode()).msg(null).data(body)
                .build();
    }
}
