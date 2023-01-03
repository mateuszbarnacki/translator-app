package com.project.translator.application.port.in;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record LanguageDetails(@NotEmpty String language) {
}
