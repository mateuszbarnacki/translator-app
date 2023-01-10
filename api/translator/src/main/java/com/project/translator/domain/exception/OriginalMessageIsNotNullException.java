package com.project.translator.domain.exception;

public class OriginalMessageIsNotNullException extends RuntimeException {

    public OriginalMessageIsNotNullException() {
        super("Original message cannot contain original message id");
    }
}