package com.project.translator.application.port.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LanguageDto {
    @Schema(description = "Language id")
    Long id;
    @Schema(description = "Language name")
    String language;
}
