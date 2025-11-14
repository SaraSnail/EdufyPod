package com.example.EdufyPod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidFieldsException extends RuntimeException {
    private String field;
    private String condition;
    private Object value;

    public ValidFieldsException(String field, String condition, Object value) {
        super(String.format("The field %s has the condition to be '%s', while the value given is [%s]", field, condition, value));
        this.field = field;
        this.condition = condition;
        this.value = value;
    }
    public ValidFieldsException(String message) {
        super(message);
    }

    public String getField() {
        return field;
    }

    public String getCondition() {
        return condition;
    }

    public Object getValue() {
        return value;
    }
}
