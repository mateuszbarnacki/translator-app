package com.project.translator.adapter.in.web;

import com.project.translator.application.port.in.LanguageDetails;
import com.project.translator.application.port.in.LanguageUseCase;
import com.project.translator.domain.LanguageDomain;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Languages")
@RequiredArgsConstructor
@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageUseCase languageUseCase;

    @Operation(summary = "Returns all languages")
    @GetMapping
    Collection<LanguageDomain> getLanguages() {
        return languageUseCase.getLanguages();
    }

    @Operation(summary = "Returns language by id")
    @GetMapping(value = "/{id}")
    LanguageDomain getById(@PathVariable("id") Long id) {
        return languageUseCase.getLanguageById(id);
    }

    @Operation(summary = "Create language")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void createLanguage(@RequestBody @Valid LanguageDetails languageDetails) {
        languageUseCase.createLanguage(languageDetails);
    }

    @Operation(summary = "Update language with given id")
    @PutMapping("/{id}")
    void updateLanguage(@PathVariable("id") Long id, @RequestBody @Valid LanguageDetails languageDetails) {
        languageUseCase.updateLanguage(id, languageDetails);
    }


    @Operation(summary = "Delete language with given id")
    @DeleteMapping("/{id}")
    void deleteLanguage(@PathVariable("id") Long id) {
        languageUseCase.deleteLanguage(id);
    }
}
