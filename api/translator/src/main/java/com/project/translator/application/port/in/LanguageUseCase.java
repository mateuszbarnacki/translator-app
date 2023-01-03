package com.project.translator.application.port.in;

import com.project.translator.domain.LanguageDomain;

import java.util.Collection;

public interface LanguageUseCase {

    Collection<LanguageDomain> getLanguages();

    LanguageDomain getLanguageById(Long id);

    void createLanguage(LanguageDetails languageDetails);

    void updateLanguage(Long id, LanguageDetails languageDetails);

    void deleteLanguage(Long id);
}
