package com.project.translator.application.mapper;

import com.project.translator.application.dto.LanguageDto;
import com.project.translator.domain.LanguageDomain;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {

    public LanguageDomain mapDtoToDomain(LanguageDto dto) {
        return LanguageDomain.builder()
                .id(dto.getId())
                .language(dto.getLanguage())
                .build();
    }

    public LanguageDto mapDomainToDto(LanguageDomain domain) {
        return LanguageDto.builder()
                .id(domain.getId())
                .language(domain.getLanguage())
                .build();
    }
}
