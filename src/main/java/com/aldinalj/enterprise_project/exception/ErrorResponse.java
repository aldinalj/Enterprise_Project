package com.aldinalj.enterprise_project.exception;

import java.util.List;

public record ErrorResponse(
        int httpStatusCode,
        String message,
        String timeStamp,
        List<ValidationError> validationErrors
) {
    public ErrorResponse(int httpStatusCode, String message, String timeStamp) {
        this(httpStatusCode, message, timeStamp, List.of());
    }

    public record ValidationError(
            String field,
            String error
    ) {}
}
