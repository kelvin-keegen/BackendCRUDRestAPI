package com.appsbykeegan.backendcrudrestapi.entity.models.records;

public record ResponseTemplate(
        Object statusCode,
        String message,
        Object data

) {
}
