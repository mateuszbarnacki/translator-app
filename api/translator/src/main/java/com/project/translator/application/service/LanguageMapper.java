package com.project.translator.application.service;

import com.project.translator.application.port.out.LanguageDto;
import com.project.translator.domain.LanguageDomain;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
class LanguageMapper {
    Collection<LanguageDto> mapDomainsToDtos(Collection<LanguageDomain> domainCollection) {
        return domainCollection.stream()
                .map(this::mapDomainToDto)
                .toList();
    }

    LanguageDto mapDomainToDto(LanguageDomain domain) {
        return LanguageDto.builder()
                .id(domain.getId())
                .language(domain.getLanguage())
                .build();
    }
}
