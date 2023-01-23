package com.project.translator.application.port.out;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LanguageDto {
    Long id;
    String language;
}
