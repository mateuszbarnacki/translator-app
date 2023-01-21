package com.project.translator.application.mapper;

import com.project.translator.application.dto.TagDto;
import com.project.translator.domain.TagDomain;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagDomain mapDtoToDomain(TagDto dto) {
        return TagDomain.builder()
                .id(dto.getId())
                .tag(dto.getTag())
                .build();
    }

    public TagDto mapDomainToDto(TagDomain domain) {
        return TagDto.builder()
                .id(domain.getId())
                .tag(domain.getTag())
                .build();
    }
}
