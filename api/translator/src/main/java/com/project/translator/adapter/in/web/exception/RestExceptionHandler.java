package com.project.translator.adapter.in.web.exception;

import com.project.translator.domain.exception.LanguageNotFoundException;
import com.project.translator.domain.exception.MessageNotFoundException;
import com.project.translator.domain.exception.MessagesSearchByLanguageNotFoundException;
import com.project.translator.domain.exception.MessagesSearchByTagNotFoundException;
import com.project.translator.domain.exception.OriginalMessageIsNotNullException;
import com.project.translator.domain.exception.OriginalMessageNotInEnglishException;
import com.project.translator.domain.exception.RestError;
import com.project.translator.domain.exception.TagNotFoundException;
import com.project.translator.domain.exception.TranslationCannotBeConvertedException;
import com.project.translator.domain.exception.TranslatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({LanguageNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestError> handleLanguageNotFoundException(LanguageNotFoundException e) {
        return new ResponseEntity<>(buildRestError(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MessageNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestError> handleMessageNotFoundException(MessageNotFoundException e) {
        return new ResponseEntity<>(buildRestError(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MessagesSearchByLanguageNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestError> handleMessagesSearchByLanguageNotFoundException(MessagesSearchByLanguageNotFoundException e) {
        return new ResponseEntity<>(buildRestError(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MessagesSearchByTagNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestError> handleMessagesSearchByTagNotFoundException(MessagesSearchByTagNotFoundException e) {
        return new ResponseEntity<>(buildRestError(e), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({TagNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestError> handleTagNotFoundException(TagNotFoundException e) {
        return new ResponseEntity<>(buildRestError(e), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({OriginalMessageIsNotNullException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestError> handleOriginalMessageIsNotNullException(OriginalMessageIsNotNullException e) {
        return new ResponseEntity<>(buildRestError(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({OriginalMessageNotInEnglishException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestError> handleOriginalMessageNotInEnglishException(OriginalMessageNotInEnglishException e) {
        return new ResponseEntity<>(buildRestError(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({TranslationCannotBeConvertedException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestError> handleTranslationCannotBeConvertedException(TranslationCannotBeConvertedException e) {
        return new ResponseEntity<>(buildRestError(e), HttpStatus.BAD_REQUEST);
    }

    private RestError buildRestError(TranslatorException e) {
        return RestError.builder()
                .message(e.getMessage())
                .errorCode(e.getErrorCode())
                .build();
    }
}
