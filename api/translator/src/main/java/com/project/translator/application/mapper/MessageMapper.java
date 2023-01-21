package com.project.translator.application.mapper;

import com.project.translator.application.dto.MessageDto;
import com.project.translator.application.port.out.LanguagePort;
import com.project.translator.application.port.out.MessagePort;
import com.project.translator.application.port.out.TagPort;
import com.project.translator.domain.LanguageDomain;
import com.project.translator.domain.MessageDomain;
import com.project.translator.domain.TagDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MessageMapper {
    private final MessagePort messagePort;
    private final LanguagePort languagePort;
    private final TagPort tagPort;

    public MessageDto mapDomainToDto(MessageDomain domain) {
        MessageDomain originalMessage = domain.getOriginalMessage();
        LanguageDomain language = domain.getLanguage();
        return MessageDto.builder()
                .id(domain.getId())
                .originalMessage(originalMessage.getId())
                .language(language.getId())
                .content(domain.getContent())
                .tags(mapMessageDomainToTagIds(domain))
                .build();
    }

    public MessageDomain mapDtoToDomain(MessageDto dto) {
        Long originalMessageId = dto.getOriginalMessage();
        Long languageId = dto.getLanguage();
        return MessageDomain.builder()
                .id(dto.getId())
                .originalMessage(mapMessageIdToMessageDomain(originalMessageId))
                .language(mapLanguageIdToLanguageDomain(languageId))
                .content(dto.getContent())
                .tags(mapMessageDtoToTagDomains(dto))
                .build();
    }

    private Set<Long> mapMessageDomainToTagIds(MessageDomain messageDomain) {
        return messageDomain.getTags().stream()
                .map(TagDomain::getId)
                .collect(Collectors.toSet());
    }

    private MessageDomain mapMessageIdToMessageDomain(Long originalMessageId) {
        return Optional.ofNullable(originalMessageId)
                .map(messagePort::getMessageById)
                .orElse(null);
    }

    private LanguageDomain mapLanguageIdToLanguageDomain(Long languageId) {
        return Optional.of(languageId)
                .map(languagePort::getLanguageById)
                .orElse(null);
    }

    private Set<TagDomain> mapMessageDtoToTagDomains(MessageDto dto) {
        return dto.getTags().stream()
                .map(tagPort::getTagById)
                .collect(Collectors.toSet());
    }
}
