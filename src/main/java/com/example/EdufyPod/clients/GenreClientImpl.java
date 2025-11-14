package com.example.EdufyPod.clients;

import com.example.EdufyPod.exceptions.CallFailException;
import com.example.EdufyPod.models.DTO.GenreDTO;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

//ED-267-SA
@Service
public class GenreClientImpl implements GenreClient {

    //ED-267-SA
    private final RestClient restClient;
    private final LoadBalancerClient loadBalancer;

    public GenreClientImpl(RestClient.Builder restClientBuilder, LoadBalancerClient loadBalancer) {
        this.restClient = restClientBuilder.build();
        this.loadBalancer = loadBalancer;
    }

    //ED-267-SA
    @Override
    public List<GenreDTO> getGenreEpisode(Long mediaId) {
        ServiceInstance serviceInstance = loadBalancer.choose("EDUFYGENRE");
        String uri = "/genre/by/media-id/PODCAST_EPISODE/" + mediaId;
        try {
            List<GenreDTO> response = restClient
                    .get()
                    .uri(serviceInstance.getUri()+uri)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<GenreDTO>>() {});

            if(response == null){
                throw new CallFailException("Genre", uri);
            }

            return response;

        }catch (RestClientResponseException e){
            throw new CallFailException("Genre", uri, String.format(e.getMessage(), e));
        }

    }
}
