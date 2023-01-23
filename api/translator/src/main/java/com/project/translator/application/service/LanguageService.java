package com.project.translator.application.service;

import com.project.common.UseCase;
import com.project.translator.application.port.in.LanguageDetails;
import com.project.translator.application.port.out.LanguageDto;
import com.project.translator.application.port.out.LanguageMapper;
import com.project.translator.application.port.in.LanguageUseCase;
import com.project.translator.application.port.out.LanguagePort;
import com.project.translator.domain.exception.LanguageNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
@UseCase
@Transactional
public class LanguageService implements LanguageUseCase {
    private final LanguagePort languagePort;
    private final LanguageMapper languageMapper;

    @Override
    public Collection<LanguageDto> getLanguages() {
        return languagePort.getLanguages().stream()
                .map(languageMapper::mapDomainToDto)
                .toList();
    }

    @Override
    public LanguageDto getLanguageById(Long id) {
        return Optional.ofNullable(id)
                .map(languagePort::getLanguageById)
                .map(languageMapper::mapDomainToDto)
                .orElseThrow(() -> new LanguageNotFoundException(id));
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
