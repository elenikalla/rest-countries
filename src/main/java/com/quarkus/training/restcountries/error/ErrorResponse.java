package com.quarkus.training.restcountries.error;

import java.util.List;

public record ErrorResponse(String message, Object details) {

    public ErrorResponse(String message, List<String> errors) {
        this(message, (Object) errors);
    }
}
