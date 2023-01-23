package com.project.translator.application.service;

import com.project.translator.application.port.out.MessageDto;
import com.project.translator.domain.LanguageDomain;
import com.project.translator.domain.MessageDomain;
import com.project.translator.domain.TagDomain;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class MessageMapperTest {
    @Autowired
    private MessageMapper mapper;

    @Test
    void shouldMapDomainToDto() {
        MessageDomain messageDomain = MessageDomain.builder()
                .id(2L)
                .originalMessage(MessageDomain.builder().id(1L).build())
                .language(LanguageDomain.builder().id(2L).language("Polish").build())
                .content("Cześć")
                .tags(Set.of(TagDomain.builder().id(1L).tag("Test").build()))
                .build();

        MessageDto dto = mapper.mapDomainToDto(messageDomain);

        assertThat(dto).hasFieldOrPropertyWithValue("id", 2L)
                .hasFieldOrPropertyWithValue("originalMessage", 1L)
                .hasFieldOrPropertyWithValue("language", 2L)
                .hasFieldOrPropertyWithValue("content", "Cześć")
                .hasFieldOrPropertyWithValue("tags", Set.of(1L));
    }

    @Test
    void shouldMapDomainToDtoWithNullOriginalMessage() {
        MessageDomain messageDomain = MessageDomain.builder()
                .id(1L)
                .originalMessage(null)
                .language(LanguageDomain.builder().id(1L).language("English").build())
                .content("Hello")
                .tags(Set.of(TagDomain.builder().id(1L).tag("Test").build()))
                .build();

        MessageDto dto = mapper.mapDomainToDto(messageDomain);

        assertThat(dto).hasFieldOrPropertyWithValue("id", 1L)
                .hasFieldOrPropertyWithValue("originalMessage", null)
                .hasFieldOrPropertyWithValue("language", 1L)
                .hasFieldOrPropertyWithValue("content", "Hello")
                .hasFieldOrPropertyWithValue("tags", Set.of(1L));
    }
}
