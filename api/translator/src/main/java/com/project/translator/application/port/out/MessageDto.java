package com.project.translator.application.port.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.Set;

@Value
@Builder
public class MessageDto {
    @Schema(description = "Message id")
    Long id;
    @Schema(description = "Original message id")
    Long originalMessage;
    @Schema(description = "Message language id")
    Long language;
    @Schema(description = "Message content")
    String content;
    @Schema(description = "List of tags id linked to message")
    @Builder.Default
    Set<Long> tags = Collections.emptySet();
}
