package com.project.translator.application.port.in;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record MessageDetails(Long original_message,
                             @NotNull Long language,
                             @NotEmpty String content,
                             List<Long> tags) {
}
