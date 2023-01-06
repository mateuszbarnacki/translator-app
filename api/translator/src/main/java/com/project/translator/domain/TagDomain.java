package com.project.translator.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TagDomain {
    Long id;
    String tag;
}
