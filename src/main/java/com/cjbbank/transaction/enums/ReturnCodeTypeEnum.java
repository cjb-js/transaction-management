package com.cjbbank.transaction.enums;

public enum ReturnCodeTypeEnum {

    /**
     * Error
     */
    Normal("Normal", "Normal"),

    /**
     * Error
     */
    Error("Error", "Error"),
    /**
     * Warn
     */
    Warn("Warn", "Warn");

    private String code;

    private String name;

    /**
     * @param code
     * @param name
     */
    ReturnCodeTypeEnum(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param code
     * @return
     */
    public static String getNameByCode(String code) {
        String name = null;
        ReturnCodeTypeEnum[] enums = ReturnCodeTypeEnum.values();
        for (ReturnCodeTypeEnum categoryEnum : enums) {
            if (categoryEnum.getCode().equals(code)) {
                name = categoryEnum.getName();
                break;
            }
        }
        return name;
    }

    /**
     * @param name
     * @return
     */
    public static String getCodeByName(String name) {
        String code = null;
        ReturnCodeTypeEnum[] enums = ReturnCodeTypeEnum.values();
        for (ReturnCodeTypeEnum categoryEnum : enums) {
            if (categoryEnum.getName().equals(name)) {
                code = categoryEnum.getCode();
                break;
            }
        }
        return code;
    }

    /**
     * @param code
     * @return
     */
    public static ReturnCodeTypeEnum getEnumByCode(String code) {
        ReturnCodeTypeEnum[] enums = ReturnCodeTypeEnum.values();
        for (ReturnCodeTypeEnum categoryEnum : enums) {
            if (categoryEnum.getCode().equals(code)) {
                return categoryEnum;
            }
        }
        return null;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return this.code + ":" + this.name;
    }
}
