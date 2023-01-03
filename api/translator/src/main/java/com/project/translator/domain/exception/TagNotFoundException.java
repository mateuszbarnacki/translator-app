package com.project.translator.domain.exception;

public class TagNotFoundException extends RuntimeException{

    public TagNotFoundException(Long id) {
        super(String.format("Tag with id=<%s> not found!", id));
    }
}
