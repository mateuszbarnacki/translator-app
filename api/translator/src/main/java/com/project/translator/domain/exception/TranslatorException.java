package com.project.translator.domain.exception;

public class TranslatorException extends RuntimeException {
    private final ErrorCode errorCode;

    public TranslatorException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
