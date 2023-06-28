package com.appsbykeegan.backendcrudrestapi.entity.models.records;

public record ResponseTemplate(
        int statusCode,
        String message,
        Object data

) {
}
