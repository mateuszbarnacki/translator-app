package com.project.translator.application.port.out;

import com.project.translator.domain.LanguageDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class LanguageMapperTest {
    @Autowired
    private LanguageMapper mapper;

    @Test
    void shouldMapDomainToDto() {
        // given
        LanguageDomain languageDomain = LanguageDomain.builder()
                .id(31L)
                .language("Spanish")
                .build();

        // when
        LanguageDto dto = mapper.mapDomainToDto(languageDomain);


        // then
        assertThat(dto).hasFieldOrPropertyWithValue("id", 31L)
                .hasFieldOrPropertyWithValue("language", "Spanish");
    }
}
