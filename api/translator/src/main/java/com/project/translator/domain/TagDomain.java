package com.project.translator.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TagDomain {

    Long id;
    String tag;
}
