package com.example.indentify_service.exception;


import com.example.indentify_service.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlingRuntimeException(RuntimeException exception) {

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(ErrorCode.UCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlingValidation(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;

        try {
            errorCode = ErrorCode.valueOf(enumKey);
        }catch (IllegalArgumentException e ){

        }
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse );
    }

    @ExceptionHandler(value = ParseException.class)
    ResponseEntity<ApiResponse> handlingValidation(ParseException exception) {
        String enumKey = exception.getMessage();

//        ErrorCode errorCode = ErrorCode.INVALID_KEY;

//        try {
//            errorCode = ErrorCode.valueOf(enumKey);
//        }catch (IllegalArgumentException e ){
//
//        }
        ApiResponse apiResponse = new ApiResponse();

//        apiResponse.setCode(errorCode.getCode());
//        apiResponse.setMessage(enumKey.getMessage());

        return ResponseEntity.badRequest().body(apiResponse );
    }
}
