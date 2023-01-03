package com.project.translator.application.port.out;

import com.project.translator.application.port.in.LanguageDetails;
import com.project.translator.domain.LanguageDomain;

import java.util.Collection;

public interface LanguagePort {

    Collection<LanguageDomain> getLanguages();

    LanguageDomain getLanguageById(Long id);

    void createLanguage(LanguageDetails languageDetails);

    void updateLanguage(Long id, LanguageDetails languageDetails);

    void deleteLanguage(Long id);
}
