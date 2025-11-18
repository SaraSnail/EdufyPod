package com.example.EdufyPod.clients;

import com.example.EdufyPod.exceptions.CallFailException;
import com.example.EdufyPod.exceptions.InvalidInputException;
import com.example.EdufyPod.exceptions.RestClientException;
import com.example.EdufyPod.models.DTO.recordOfMedia.RecordOfCreatorDTO;
import com.example.EdufyPod.models.DTO.callDTOs.GenreDTO;
import com.example.EdufyPod.models.enums.MediaType;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import java.util.List;

//ED-267-SA
@Service
public class GenreClientImpl implements GenreClient {

    //ED-267-SA
    private final RestClient restClient;
    private final LoadBalancerClient loadBalancer;

    private final String lbGenre = "EDUFYGENRE";//ED-232-SA


    public GenreClientImpl(RestClient.Builder restClientBuilder, LoadBalancerClient loadBalancer) {
        this.restClient = restClientBuilder.build();
        this.loadBalancer = loadBalancer;
    }

    //ED-267-SA
    @Override
    public List<GenreDTO> getGenreEpisode(Long mediaId) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbGenre);
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


    //ED-232-SA
    @Override
    public void createRecordOfMedia(Long mediaId, MediaType mediaType, List<Long> genreIds) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbGenre);
        String uri = "/genre/media/record";

        try{
            ResponseEntity<Void> response = restClient.post()
                    .uri(serviceInstance.getUri()+uri)
                    .body(new RecordOfCreatorDTO(mediaId, mediaType, genreIds))
                    .retrieve()
                    .toBodilessEntity();

            if(!response.getStatusCode().equals(HttpStatus.CREATED)){
                throw new IllegalStateException("Unexpected response status code: " + response.getStatusCode());
            }
        }catch (RestClientResponseException e){
            String error = e.getResponseBodyAsString();
            throw new InvalidInputException("Edufy Genre service error: " + error);
        }catch (ResourceAccessException e){
            throw new RestClientException("Edufy Pod", "Edufy Genre");
        }
    }

    //ED-232-SA
    @Override
    public GenreDTO getGenreById(Long genreId) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbGenre);
        String uri = "/api/v1/genre/"+genreId;
        try {

            return restClient.get()
                    .uri(serviceInstance.getUri()+uri)
                    .retrieve()
                    .body(GenreDTO.class);

        }catch (RestClientResponseException e){
            throw new CallFailException("Genre", uri, String.format(e.getMessage(), e));
        }catch (ResourceAccessException e){
            throw new RestClientException("Edufy Pod", "Edufy Genre");
        }
    }


}
