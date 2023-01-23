package com.project.translator.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TagDomain {

    @Schema(description = "Tag id")
    Long id;
    @Schema(description = "Tag name")
    String tag;
}
