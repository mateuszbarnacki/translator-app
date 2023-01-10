package com.project.translator.domain.exception;

public class TranslationCannotBeConvertedException extends RuntimeException {

    public TranslationCannotBeConvertedException() {
        super("Message translation cannot be converted to original message");
    }
}
