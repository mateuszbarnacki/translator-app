package com.project.translator.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.Set;

@Value
@Builder
public class LanguageDomain {
    Long id;
    String language;
}
