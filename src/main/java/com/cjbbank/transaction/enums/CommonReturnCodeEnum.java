package com.cjbbank.transaction.enums;

import org.springframework.http.HttpStatus;

public enum CommonReturnCodeEnum {

    SUCCESS("0", ReturnCodeTypeEnum.Normal, HttpStatus.OK, "success"),

    /**
     * Common catchable internal error
     */
    SYSTEM_ERROR("sys-0000", ReturnCodeTypeEnum.Error, HttpStatus.SERVICE_UNAVAILABLE,
            "Unknown service error has occurred. Please contact your administrator."),

    /**
     * No login info
     */
    NO_AUTHENTICATION("sys-0001", ReturnCodeTypeEnum.Error, HttpStatus.UNAUTHORIZED, "You are not logged in."),

    /**
     * No permission
     */
    NOT_AUTHORIZED("sys-0002", ReturnCodeTypeEnum.Error, HttpStatus.FORBIDDEN,
            "You are not authorized to access this resource, resource id:{0}"),

    /**
     *  域不能为空
     */
    REQUIRED_FIELD_EMPTY("sys-0003", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "Required field(s) should not be empty:{0}"),

    /**
     *  域不合法
     */
    INPUT_FIELD_INVALID("sys-0004", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "Invalid input field: {0}"),

    REQUEST_BODY_EMPTY("sys-0005", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "Empty request body"),

    /**
     * No permission
     */

    ARGUMENTS_INVALID("sys-0006", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "Invalid arguments {0}"),
    READ_FROM_REQUEST_CONTEXT_FAILED("sys-0007", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "Failed to read request context"),
    TENANT_QUOTA_NOT_ENOUGH("sys-0008", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "No quota to create tenant"),

    /**
     * Identity User Data not found, usually used by get entity by id related logic
     */
    IDENTITY_USER_DATA_NOT_FOUND("sys-0009", ReturnCodeTypeEnum.Error, HttpStatus.NOT_FOUND,
            "Identity user data not found"),
    IDENTITY_PROVIDER_EMAIL_DUPLICATE("sys-0010", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "Identity Provider's email suffix(es) duplicated"),
    CREATE_TENANT_LIMITATION("sys-0011", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "Not allowed to create tenant since meet the limitation number"),

    UNKNOWN_LANGUAGE_CODE("sys-0012", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "Unknown language code, it must be an ISO 639 code!"),

    DATA_NOT_FOUND("sys-0013", ReturnCodeTypeEnum.Error, HttpStatus.NOT_FOUND, "{0} is not found."),

    DATA_DUPLICATE("sys-0014", ReturnCodeTypeEnum.Error, HttpStatus.CONFLICT, "Data is duplicated: {0}."),

    APP_ID_INVALID("sys-0015", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "Invalid app id : {0}."),
    QUERY_BODY_INVALID("sys-0016", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "Invalid query body."),
    QUERY_PROPERTY_INVALID("sys-0017", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "Invalid property: {0}."),
    QUERY_PROPERTY_NOT_SUPPORTED("sys-0018", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "Not supported property: {0}."),

    GENERATE_RANDOM_STRING_FAILED_FOR_COPY("sys-0019", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "Failed to generate random string for {0} copy."),

    SERVER_ERROR_WITH_MSG("sys-0020", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "Internal Server Error: {0}"),
    FILE_IO_EXCEPTION("sys-0021", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "Failed to operate the file: {0}"),

    //来自spring 异常的 org.springframework.dao.DataIntegrityViolationException
    DATA_INTEGRITY_VIOLATION_EXCEPTION("sys-0022", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "data integrity violation: {0}"),

    DB_FIELD_NAME_CONVERT_ERROR("sys-0023", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST,
            "failed to convert java property to db field name: {0}"),
    BAD_ORDER_BY_SQL("sys-0024", ReturnCodeTypeEnum.Error, HttpStatus.BAD_REQUEST, "bad order by sql: {0}");

    public final ReturnCodeHolder VALUE;

    CommonReturnCodeEnum(String code, ReturnCodeTypeEnum type, HttpStatus httpStatus, String errorMsg) {

        VALUE = new ReturnCodeHolder(code, httpStatus, errorMsg, type);

    }

}
