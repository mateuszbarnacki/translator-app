package com.project.translator.application.port.out;

import lombok.Builder;
import lombok.Value;

import java.util.Collections;
import java.util.Set;

@Value
@Builder
public class MessageDto {
    Long id;
    Long originalMessage;
    Long language;
    String content;
    @Builder.Default
    Set<Long> tags = Collections.emptySet();
}
