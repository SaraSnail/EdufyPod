package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.tokens.TokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

@Component
public class KeycloakImpl implements Keycloak {

    private final RestClient restClient;
    private final String keycloakUrl;
    private final String podClientSecret;// ED-357-SA : Changed name on client-secret på specify which secret
    private final String clientId;

    public KeycloakImpl(RestClient.Builder restClientBuilder,
                        @Value("${keycloak.url}") String keycloakUrl,
                        @Value("${keycloak.client-id}") String clientId,
                        @Value("${keycloak.pod-client-secret}") String podClientSecret) {
        this.restClient = restClientBuilder.build();
        this.keycloakUrl = keycloakUrl;
        this.clientId = clientId;
        this.podClientSecret = podClientSecret;// ED-357-SA : Changed name on client-secret på specify which secret
    }

    @Override
    public String getAccessToken() {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "client_credentials");
        formData.add("client_id", clientId);
        formData.add("client_secret", podClientSecret);

        TokenResponse tokenResponse = restClient
                .post()
                .uri(keycloakUrl + "/realms/edufy_realm/protocol/openid-connect/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(formData)
                .retrieve()
                .body(TokenResponse.class);

        // Return access token
        assert tokenResponse != null;
        return tokenResponse.accessToken();
    }
}
