package com.momentu.momentuapi.common;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ErrorResponse {
    private final HttpStatus httpStatus;
    private final String message;
    private final ErrorCode errorCode;
    private final Date timestamp;

    public ErrorResponse(final String message, final ErrorCode errorCode, final HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }

    public static ErrorResponse of(final String message, final ErrorCode errorCode, HttpStatus status) {
        return new ErrorResponse(message, errorCode, status);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
