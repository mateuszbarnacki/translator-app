package com.project.translator.domain.exception;

public class MessageNotFoundException extends RuntimeException{

    public MessageNotFoundException(Long id) {
        super(String.format("Message with id=<%s> not found!", id));
    }
}
