package com.project.translator.application.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LanguageDto {
    Long id;
    String language;
}
