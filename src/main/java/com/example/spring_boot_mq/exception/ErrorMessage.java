package com.example.spring_boot_mq.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private int statusCode;
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private Map<String, String> errors;
}