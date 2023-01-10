package com.project.translator.application.port.in;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record TagDetails(@NotEmpty String tag) {
}
