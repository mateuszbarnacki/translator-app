package com.project.translator.domain.exception;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestError {
    String message;
    ErrorCode errorCode;
}
