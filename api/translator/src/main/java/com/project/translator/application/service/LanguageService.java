package com.project.translator.application.service;

import com.project.common.UseCase;
import com.project.translator.application.port.in.LanguageDetails;
import com.project.translator.application.port.out.LanguageDto;
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
    private final LanguageMapper languageMapper;

    @Override
    public Collection<LanguageDto> getLanguages() {
        Collection<LanguageDomain> domains = languagePort.getLanguages();
        return languageMapper.mapDomainsToDtos(domains);
    }

    @Override
    public LanguageDto getLanguageById(Long id) {
        LanguageDomain domain = languagePort.getLanguageById(id);
        return languageMapper.mapDomainToDto(domain);
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
