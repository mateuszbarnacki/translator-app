package com.project.translator.adapter.in.web;

import com.project.translator.application.port.out.LanguageDto;
import com.project.translator.application.port.in.LanguageDetails;
import com.project.translator.application.port.in.LanguageUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RequiredArgsConstructor
@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageUseCase languageUseCase;

    @GetMapping
    Collection<LanguageDto> getLanguages() {
        return languageUseCase.getLanguages();
    }

    @GetMapping(value = "/{id}")
    LanguageDto getById(@PathVariable("id") Long id) {
        return languageUseCase.getLanguageById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createLanguage(@RequestBody @Valid LanguageDetails languageDetails) {
        languageUseCase.createLanguage(languageDetails);
    }

    @PutMapping("/{id}")
    void updateLanguage(@PathVariable("id") Long id, @RequestBody @Valid LanguageDetails languageDetails) {
        languageUseCase.updateLanguage(id, languageDetails);
    }

    @DeleteMapping("/{id}")
    void deleteLanguage(@PathVariable("id") Long id) {
        languageUseCase.deleteLanguage(id);
    }
}
