package com.example.EdufyPod.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//ED-303-SA
@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
public class CallFailException extends RuntimeException {
    private String service;
    private String originalURL;
    private String values;

    //ED-303-SA
    public CallFailException(String message) {
        super(message);
    }

    //ED-303-SA
    public CallFailException(String service, String originalURL) {
        super(String.format("Response from service %s returned null on url call to: %s", service, originalURL));
        this.service = service;
        this.originalURL = originalURL;
    }

    //ED-303-SA
    public CallFailException(String service, String originalURL, String values) {
        super(String.format("Response from service %s returned null on url call to: %s. \nError: %s", service, originalURL, values));
        this.service = service;
        this.originalURL = originalURL;
        this.values = values;
    }


}
