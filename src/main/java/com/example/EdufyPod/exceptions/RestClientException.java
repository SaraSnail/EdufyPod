package com.example.EdufyPod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ED-232-SA
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class RestClientException extends RuntimeException {
    private final String resource;
    private final String field;

    public RestClientException(String resource, String field) {
        super(String.format("%s could not reach %s", resource, field));
        this.resource = resource;
        this.field = field;
    }

    public String getResource() {
        return resource;
    }

    public String getField() {
        return field;
    }
}
