package com.project.translator.domain.exception;

public class OriginalMessageIsNotNullException extends TranslatorException {

    public OriginalMessageIsNotNullException() {
        super("Original message cannot contain original message id", ErrorCode.BAD_REQUEST);
    }
}