package com.project.translator.domain.exception;

public class LanguageNotFoundException extends RuntimeException {

    public LanguageNotFoundException(Long id) {
        super(String.format("Language with id=<%s> not found!", id));
    }
}
