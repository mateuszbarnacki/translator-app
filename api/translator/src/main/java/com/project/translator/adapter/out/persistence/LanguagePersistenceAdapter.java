package com.project.translator.adapter.out.persistence;

import com.project.common.PersistenceAdapter;
import com.project.translator.application.port.in.LanguageDetails;
import com.project.translator.application.port.out.LanguagePort;
import com.project.translator.domain.LanguageDomain;
import com.project.translator.domain.exception.LanguageNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@PersistenceAdapter
class LanguagePersistenceAdapter implements LanguagePort {

    private final LanguageRepository languageRepository;

    private final TranslatorMapper translatorMapper;

    @Override
    public Collection<LanguageDomain> getLanguages() {
        log.info("Loading all languages...");

        final var languageEntities = languageRepository.findAll();
        return languageEntities.stream()
                .map(translatorMapper::toLanguageDomain)
                .collect(Collectors.toList());
    }

    @Override
    public LanguageDomain getLanguageById(@NotNull Long id) {
        log.info("Loading language with id = <{}>", id);

        return translatorMapper.toLanguageDomain(languageRepository.findById(id)
                .orElseThrow(() -> new LanguageNotFoundException(id)));
    }

    @Override
    public void createLanguage(@NotNull LanguageDetails languageDetails) {
        log.info("Creating language from language details = <{}>", languageDetails);

        final var languageEntity = new LanguageEntity();
        languageEntity.setLanguage(languageDetails.language());
        languageRepository.save(languageEntity);

        log.info("Language created successfully = <{}>", languageEntity);
    }

    @Override
    public void updateLanguage(@NotNull Long id, @NotNull LanguageDetails languageDetails) {
        log.info("Updating language from language details = <{}> with id = <{}>",
                languageDetails, id);

        final var languageEntity = languageRepository.findById(id)
                .orElseThrow(() -> new LanguageNotFoundException(id));
        languageEntity.setLanguage(languageDetails.language());

        log.info("Language updated successfully = <{}>", languageEntity);
    }

    @Override
    public void deleteLanguage(@NotNull Long id) {
        log.info("Deleting language with id = <{}>", id);

        languageRepository.deleteById(id);

        log.info("Language deleted successfully with id = <{}>", id);
    }
}
