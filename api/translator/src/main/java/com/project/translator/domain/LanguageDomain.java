package com.project.translator.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LanguageDomain {

    @Schema(description = "Language id")
    Long id;
    @Schema(description = "Language name")
    String language;
}
