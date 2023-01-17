package com.project.translator.domain.exception;

public class OriginalMessageNotInEnglishException extends TranslatorException {

    public OriginalMessageNotInEnglishException(String language) {
        super(String.format("Language of message: %s. " +
                "Only messages in English can be original messages.", language), ErrorCode.BAD_REQUEST);
    }
}
