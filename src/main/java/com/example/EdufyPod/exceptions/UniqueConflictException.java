package com.example.EdufyPod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ED-232-SA
@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueConflictException extends RuntimeException {
    private String object;
    private Object value;

    public UniqueConflictException(String Object, Object value) {
        super(String.format("%s: [%s] already exists, duplicates is not allowed"));
        this.object = object;
        this.value = value;
    }

    public String getObject() {
        return object;
    }

    public Object getValue() {
        return value;
    }
}
