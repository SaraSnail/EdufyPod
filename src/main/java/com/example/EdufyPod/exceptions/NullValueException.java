package com.example.EdufyPod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NullValueException extends RuntimeException {
    private String field;
    private Object value;

    public NullValueException(String field, Object value) {
        super(String.format("The field %s cannot have the value %s", field, value));
        this.field = field;
        this.value = value;
    }
    public NullValueException(String message) {
        super(message);
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
