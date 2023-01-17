package com.project.translator.adapter.in.web.exception;

import com.project.translator.domain.exception.LanguageNotFoundException;
import com.project.translator.domain.exception.MessageNotFoundException;
import com.project.translator.domain.exception.OriginalMessageIsNotNullException;
import com.project.translator.domain.exception.OriginalMessageNotInEnglishException;
import com.project.translator.domain.exception.RestError;
import com.project.translator.domain.exception.TagNotFoundException;
import com.project.translator.domain.exception.TranslationCannotBeConvertedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
class DummyController {

    @GetMapping("/languageNotFound")
    ResponseEntity<RestError> dummyLanguageNotFoundController() {
        throw new LanguageNotFoundException(-8080L);
    }

    @GetMapping("/messageNotFound")
    ResponseEntity<RestError> dummyMessageNotFoundController() {
        throw new MessageNotFoundException(-19990L);
    }

    @GetMapping("/tagNotFound")
    ResponseEntity<RestError> dummyTagNotFoundController() {
        throw new TagNotFoundException(-1L);
    }

    @GetMapping("/originalMessageNotNull")
    ResponseEntity<RestError> dummyOriginalMessageNotNullController() {
        throw new OriginalMessageIsNotNullException();
    }

    @GetMapping("/translationCannotBeConverted")
    ResponseEntity<RestError> dummyTranslationCannotBeConvertedController() {
        throw new TranslationCannotBeConvertedException();
    }

    @GetMapping("/originalMessageNotInEnglish")
    ResponseEntity<RestError> dummyOriginalMessageNotInEnglishController() {
        throw new OriginalMessageNotInEnglishException("Polish");
    }
}
