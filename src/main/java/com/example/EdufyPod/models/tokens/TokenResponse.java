package com.example.EdufyPod.models.tokens;

import com.fasterxml.jackson.annotation.JsonProperty;

//ED-348-SA
public record TokenResponse(
        @JsonProperty("access_token") String accessToken) {
}
