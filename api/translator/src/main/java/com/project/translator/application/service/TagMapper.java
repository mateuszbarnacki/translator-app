package com.project.translator.application.service;

import com.project.translator.application.port.out.TagDto;
import com.project.translator.domain.TagDomain;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
class TagMapper {
    Collection<TagDto> mapDomainsToDtos(Collection<TagDomain> domainCollection) {
        return domainCollection.stream()
                .map(this::mapDomainToDto)
                .toList();
    }

    TagDto mapDomainToDto(TagDomain domain) {
        return TagDto.builder()
                .id(domain.getId())
                .tag(domain.getTag())
                .build();
    }
}
