package com.project.translator.application.port.in;

import com.project.translator.application.port.out.LanguageDto;

import java.util.Collection;

public interface LanguageUseCase {

    Collection<LanguageDto> getLanguages();

    LanguageDto getLanguageById(Long id);

    void createLanguage(LanguageDetails languageDetails);

    void updateLanguage(Long id, LanguageDetails languageDetails);

    void deleteLanguage(Long id);
}
