package com.project.translator.domain.exception;

public class MessagesSearchByTagNotFoundException extends TranslatorException {

    public MessagesSearchByTagNotFoundException(String tag) {
        super(String.format("Message for tag=<%s> not found!", tag), ErrorCode.NOT_FOUND);
    }
}
