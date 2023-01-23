package com.project.translator.application.port.out;

import com.project.translator.domain.LanguageDomain;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper {
    public LanguageDto mapDomainToDto(LanguageDomain domain) {
        return LanguageDto.builder()
                .id(domain.getId())
                .language(domain.getLanguage())
                .build();
    }
}
