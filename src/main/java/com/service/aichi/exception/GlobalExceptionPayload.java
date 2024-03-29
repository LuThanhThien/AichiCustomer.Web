package com.service.aichi.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class GlobalExceptionPayload {
    private final String message;

    private final Throwable throwable;

    private final HttpStatus httpStatus;

    private final LocalDateTime timeStamp = LocalDateTime.now();
}
