package com.project.translator.adapter.out.persistence;

import com.project.translator.application.port.in.LanguageDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@Import({LanguagePersistenceAdapter.class})
class LanguagePersistenceAdapterTest {
    private static final String ENGLISH_LANGUAGE = "English";
    private static final String POLISH_LANGUAGE = "Polish";

    @Autowired
    private LanguagePersistenceAdapter adapter;

    @Autowired
    private LanguageRepository languageRepository;

    @MockBean
    private TranslatorMapper translatorMapper;

    @BeforeEach
    void setup() {
        adapter.createLanguage(createLanguageDetails(ENGLISH_LANGUAGE));
    }

    @Test
    void languageShouldBeCreatedSuccessfully() {
        assertThat(languageRepository.findAll()).hasSize(1);
    }

    @Test
    void languageShouldBeUpdatedSuccessfully() {
        final var languageEntities = languageRepository.findAll();
        assertEquals(ENGLISH_LANGUAGE, languageEntities.get(0).getLanguage());

        adapter.updateLanguage(languageEntities.get(0).getId(),
                createLanguageDetails(POLISH_LANGUAGE));
        final var updatedLanguageEntity = languageRepository.findAll();
        assertEquals(updatedLanguageEntity.get(0).getLanguage(), POLISH_LANGUAGE);
    }

    @Test
    void languageShouldBeDeletedSuccessfully() {
        assertThat(languageRepository.findAll()).hasSize(1);
        adapter.deleteLanguage(languageRepository.findAll().get(0).getId());
        assertThat(languageRepository.findAll()).hasSize(0);
    }

    @Test
    void shouldFindAllLanguages() {
        adapter.createLanguage(createLanguageDetails(POLISH_LANGUAGE));
        assertThat(adapter.getLanguages()).hasSize(2);
    }

    private LanguageDetails createLanguageDetails(String language) {
        return new LanguageDetails(language);
    }
}
