package dev.commonlib.response;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record ExceptionResponse(
        HttpStatus status,
        String exceptionClassName,
        String message
) {
}

