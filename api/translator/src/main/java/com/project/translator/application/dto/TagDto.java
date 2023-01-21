package com.project.translator.application.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TagDto {
    Long id;
    String tag;
}
