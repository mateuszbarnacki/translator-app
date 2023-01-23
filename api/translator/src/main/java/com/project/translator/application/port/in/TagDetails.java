package com.project.translator.application.port.in;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record TagDetails(@Schema(description = "Name of tag") @NotEmpty String tag) {
}
