package com.example.indentify_service.exception;

public enum ErrorCode {
    USER_EXISTED(6969, "USER EXISTED"),
    USER_NOT_EXISTED(6969, "USER NOT EXISTED"),
    INVALID_KEY(1005 , "INVALID MESSAGE KEY"),
    UCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED ERROR "),
    USERNAME_UNVALID(1003, "USERNAME MUST BE AT LEAST 3 CHARACTERS"),
    PASSWORD_UNVALID(1004, "PASSWORD MUST BE AT LEAST 8 CHARACTERS"),
    UNAUTHENTICATED_ERROR(420, "PASSWORD MUST BE AT LEAST 8 CHARACTERS");

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
