package com.project.translator.application.service;

import com.project.common.UseCase;
import com.project.translator.application.port.in.LanguageDetails;
import com.project.translator.application.port.in.LanguageUseCase;
import com.project.translator.application.port.out.LanguagePort;
import com.project.translator.domain.LanguageDomain;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
@UseCase
@Transactional
public class LanguageService implements LanguageUseCase {

    private final LanguagePort languagePort;

    @Override
    public Collection<LanguageDomain> getLanguages() {
        return languagePort.getLanguages();
    }

    @Override
    public LanguageDomain getLanguageById(Long id) {
        return languagePort.getLanguageById(id);
    }

    @Override
    public void createLanguage(LanguageDetails languageDetails) {
        languagePort.createLanguage(languageDetails);
    }

    @Override
    public void updateLanguage(Long id, LanguageDetails languageDetails) {
        languagePort.updateLanguage(id, languageDetails);
    }

    @Override
    public void deleteLanguage(Long id) {
        languagePort.deleteLanguage(id);
    }
}
