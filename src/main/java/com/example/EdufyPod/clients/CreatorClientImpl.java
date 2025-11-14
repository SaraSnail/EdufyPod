package com.example.EdufyPod.clients;

import com.example.EdufyPod.exceptions.CallFailException;
import com.example.EdufyPod.models.DTO.body.CreatorBody;
import com.example.EdufyPod.models.DTO.callDTOs.CreatorDTO;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastDTO;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastSeasonDTO;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

//ED-303-SA
@Service
public class CreatorClientImpl implements CreatorClient {

    private final RestClient restClient;
    private final LoadBalancerClient loadBalancer;//ED-276-SA

    //ED-232-SA
    private WebClient webClient;

    public CreatorClientImpl(RestClient.Builder restClientBuilder, LoadBalancerClient loadBalancerClient, WebClient.Builder webClientBuilder) {
        this.restClient = restClientBuilder.build();
        this.loadBalancer = loadBalancerClient;
        this.webClient = webClientBuilder.build();
    }

    //ED-303-SA
    @Override
    public List<TransferPodcastDTO> transferPodcastDTOs(Long creatorId) {
        ServiceInstance serviceInstance = loadBalancer.choose("EDUFYCREATOR");//ED-276-SA
        String uri = "/creator/media-creator/" + creatorId + "/PODCAST_EPISODE";

        try{
            List<TransferPodcastDTO> response = restClient
                    .get()
                    .uri(serviceInstance.getUri()+uri)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<TransferPodcastDTO>>() {});

            if(response == null){
                throw new CallFailException("Creator", uri);
            }

            return response;

        } catch (RestClientResponseException e) {
            throw new CallFailException("Creator", uri, String.format(e.getMessage(), e));
        }

    }

    //ED-303-SA
    @Override
    public List<TransferPodcastSeasonDTO> transferPodcastSeasonDTOs(Long creatorId) {
        ServiceInstance serviceInstance = loadBalancer.choose("EDUFYCREATOR");//ED-276-SA
        String uri = "/creator/media-creator/" + creatorId + "/PODCAST_SEASON";

        try{
            List<TransferPodcastSeasonDTO> response = restClient
                    .get()
                    .uri(serviceInstance.getUri()+uri)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<TransferPodcastSeasonDTO>>() {});

            if(response == null){
                throw new CallFailException("Creator", uri);
            }

            return response;

        } catch (RestClientResponseException e) {
            throw new CallFailException("Creator", uri, String.format(e.getMessage(), e));
        }
    }

    //ED-303-SA
    @Override
    public List<CreatorDTO> getCreatorsEpisode(Long mediaId) {
        ServiceInstance serviceInstance = loadBalancer.choose("EDUFYCREATOR");//ED-276-SA
        String uri = "/creator/creators-media/PODCAST_EPISODE/" + mediaId;
        try{
            List<CreatorDTO> response = restClient
                    .get()
                    .uri(serviceInstance.getUri()+uri)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<CreatorDTO>>() {});

            if(response == null){
                throw new CallFailException("Creator", uri);
            }

            return response;

        } catch (RestClientResponseException e) {
            throw new CallFailException("Creator", uri, String.format(e.getMessage(), e));
        }
    }

    //ED-303-SA
    @Override
    public List<CreatorDTO> getCreatorsSeason(Long mediaId) {
        ServiceInstance serviceInstance = loadBalancer.choose("EDUFYCREATOR");//ED-276-SA
        String uri = "/creator/creators-media/PODCAST_SEASON/" + mediaId;
        try{
            List<CreatorDTO> response = restClient
                    .get()
                    .uri(serviceInstance.getUri()+uri)
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<CreatorDTO>>() {});

            if(response == null){
                throw new CallFailException("Creator", uri);
            }

            return response;

        } catch (RestClientResponseException e) {
            throw new CallFailException("Creator", uri, String.format(e.getMessage(), e));
        }
    }

    //ED-232-SA
    @Override
    public void registerWithCreator(CreatorBody body) {
        webClient.post()
                .uri("http://EDUFYCREATOR/creator/media/record")
                .bodyValue(body)
                .retrieve()
                .bodyToMono(Void.class)
                .block();

    }
}
