package com.project.translator.application.port.out;

import com.project.translator.domain.TagDomain;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public TagDto mapDomainToDto(TagDomain domain) {
        return TagDto.builder()
                .id(domain.getId())
                .tag(domain.getTag())
                .build();
    }
}
