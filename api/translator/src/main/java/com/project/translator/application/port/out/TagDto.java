package com.project.translator.application.port.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TagDto {
    @Schema(description = "Tag id")
    Long id;
    @Schema(description = "Tag name")
    String tag;
}
