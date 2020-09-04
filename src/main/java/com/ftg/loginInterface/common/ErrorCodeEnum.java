package com.ftg.loginInterface.common;

import lombok.Getter;

import java.util.Arrays;

public enum ErrorCodeEnum {
    SYSTEM_DEFAULT_ERROR(1, "对不起，系统发生了异常，请您反馈给平台"),
    INVALID_TOKEN (1, "非法token"),
    ;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Getter
    private int code;
    @Getter
    private String msg;

    public static String message(int code) {
        return Arrays.stream(values())
                .filter(l-> l.code == code)
                .findFirst()
                .map(ErrorCodeEnum::getMsg)
                .orElse(null);
    }

    public static void main(String[] args) {
        for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
            System.out.println(errorCodeEnum.getMsg());
        }
    }
}
