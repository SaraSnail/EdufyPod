package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.DTO.body.ThumbBody;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ThumbClientImpl implements ThumbClient {

    private WebClient webClient;

    public ThumbClientImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Override
    public void registerWithThumb(ThumbBody body) {
        webClient.post()
                .uri("http://EDUFYTHUMB/thumb/media/record")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
