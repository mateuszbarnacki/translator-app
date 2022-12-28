package com.project.translator.domain;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.Set;

@Value
@Builder
public class MessageDomain {
    Long id;
    MessageDomain originalMessage;
    LanguageDomain language;
    String content;
    @Builder.Default
    Set<TagDomain> tags = Collections.emptySet();
}
