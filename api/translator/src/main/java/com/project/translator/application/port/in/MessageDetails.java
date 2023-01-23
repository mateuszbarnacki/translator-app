package com.project.translator.application.port.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.List;

@Builder
public record MessageDetails(@Schema(description = "Original message id") Long original_message,
                             @Schema(description = "Message language id") @NotNull Long language,
                             @Schema(description = "Content of message, actual \"message\"") @NotEmpty String content,
                             @Schema(description = "List of tags ids which should be linked to message") List<Long> tags) {
}
