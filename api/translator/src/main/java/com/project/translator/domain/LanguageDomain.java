package com.project.translator.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LanguageDomain {
    Long id;
    String language;
}
