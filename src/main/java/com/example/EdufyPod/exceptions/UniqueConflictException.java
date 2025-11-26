package com.example.EdufyPod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ED-232-SA
@ResponseStatus(HttpStatus.CONFLICT)
public class UniqueConflictException extends RuntimeException {
    private String object;
    private Object value;

    //ED-343-SA: had missed object and value in the String.format
    public UniqueConflictException(String object, Object value) {
        super(String.format("%s: [%s] already exists, duplicates is not allowed", object,value));
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
