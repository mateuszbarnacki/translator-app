package com.project.translator.domain.exception;

public class MessagesSearchByLanguageNotFoundException extends TranslatorException {

    public MessagesSearchByLanguageNotFoundException(String language) {
        super(String.format("Message for language=<%s> not found!", language), ErrorCode.NOT_FOUND);
    }
}
