package com.project.translator.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.Set;

@Value
@Builder
public class MessageDomain {

    @Schema(description = "Message id")
    Long id;
    @Schema(description = "Original message")
    MessageDomain originalMessage;
    @Schema(description = "Message language")
    LanguageDomain language;
    @Schema(description = "Message content")
    String content;
    @Schema(description = "List of tags linked to message")
    @Builder.Default
    Set<TagDomain> tags = Collections.emptySet();
}
