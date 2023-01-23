package com.project.translator.application.service;

import com.project.translator.application.port.out.MessageDto;
import com.project.translator.domain.LanguageDomain;
import com.project.translator.domain.MessageDomain;
import com.project.translator.domain.TagDomain;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
class MessageMapper {
    Collection<MessageDto> mapDomainsToDtos(Collection<MessageDomain> domainCollection) {
        return domainCollection.stream()
                .map(this::mapDomainToDto)
                .toList();
    }

    MessageDto mapDomainToDto(MessageDomain domain) {
        MessageDomain originalMessage = domain.getOriginalMessage();
        LanguageDomain language = domain.getLanguage();
        return MessageDto.builder()
                .id(domain.getId())
                .originalMessage(getOriginalMessageId(originalMessage))
                .language(getLanguageId(language))
                .content(domain.getContent())
                .tags(mapMessageDomainToTagIds(domain))
                .build();
    }

    private Set<Long> mapMessageDomainToTagIds(MessageDomain messageDomain) {
        return messageDomain.getTags().stream()
                .map(TagDomain::getId)
                .collect(Collectors.toSet());
    }

    private Long getOriginalMessageId(MessageDomain messageDomain) {
        return Objects.isNull(messageDomain) ? null : messageDomain.getId();
    }

    private Long getLanguageId(LanguageDomain languageDomain) {
        return Objects.isNull(languageDomain) ? null : languageDomain.getId();
    }
}
