package com.project.translator.domain.exception;

public class TranslationCannotBeConvertedException extends TranslatorException {

    public TranslationCannotBeConvertedException() {
        super("Message translation cannot be converted to original message", ErrorCode.BAD_REQUEST);
    }
}
