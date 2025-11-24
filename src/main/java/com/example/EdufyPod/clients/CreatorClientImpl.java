package com.example.EdufyPod.clients;

import com.example.EdufyPod.exceptions.CallFailException;
import com.example.EdufyPod.exceptions.InvalidInputException;
import com.example.EdufyPod.exceptions.RestClientException;
import com.example.EdufyPod.models.DTO.recordOfMedia.RecordOfCreatorDTO;
import com.example.EdufyPod.models.DTO.callDTOs.CreatorDTO;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastDTO;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastSeasonDTO;
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

//ED-303-SA
@Service
public class CreatorClientImpl implements CreatorClient {

    private final RestClient restClient;
    private final LoadBalancerClient loadBalancer;//ED-276-SA

    private final String lbCreator = "EDUFYCREATOR";//ED-232-SA
    private final Keycloak keycloak;//ED-348-SA

    public CreatorClientImpl(RestClient.Builder restClientBuilder, LoadBalancerClient loadBalancerClient, Keycloak keycloakImpl) {
        this.restClient = restClientBuilder.build();
        this.loadBalancer = loadBalancerClient;
        this.keycloak = keycloakImpl;
    }

    //ED-303-SA
    @Override
    public List<TransferPodcastDTO> transferPodcastDTOs(Long creatorId) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbCreator);//ED-276-SA
        String uri = "/creator/mediabycreator/" + creatorId + "/"+MediaType.PODCAST_EPISODE;
        String token = keycloak.getAccessToken();//ED-348-SA

        try{
            List<TransferPodcastDTO> response = restClient
                    .get()
                    .uri(serviceInstance.getUri()+"/creator/mediabycreator/" + creatorId + "/"+MediaType.PODCAST_EPISODE)
                    .header("Authorization", "Bearer "+token)//ED-384-SA
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<TransferPodcastDTO>>() {});

            if(response == null){
                throw new CallFailException("Creator", uri);
            }

            return response;

        } catch (RestClientResponseException e) {
            throw new CallFailException("Creator", uri, String.format(e.getMessage(), e));
        }catch (RestClientException e){
            throw new RestClientException("Edufy Pod", "Edufy Creator");
        }

    }

    //ED-303-SA
    @Override
    public List<TransferPodcastSeasonDTO> transferPodcastSeasonDTOs(Long creatorId) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbCreator);//ED-276-SA
        String uri = "/creator/mediabycreator/" + creatorId + "/"+MediaType.PODCAST_SEASON;
        String token = keycloak.getAccessToken();//ED-348-SA

        try{
            List<TransferPodcastSeasonDTO> response = restClient
                    .get()
                    .uri(serviceInstance.getUri()+"/creator/mediabycreator/" + creatorId + "/"+MediaType.PODCAST_SEASON)
                    .header("Authorization", "Bearer "+token)//ED-348-SA
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<TransferPodcastSeasonDTO>>() {});

            if(response == null){
                throw new CallFailException("Creator", uri);
            }

            return response;

        } catch (RestClientResponseException e) {
            throw new CallFailException("Creator", uri, String.format(e.getMessage(), e));
        }catch (RestClientException e){
            throw new RestClientException("Edufy Pod", "Edufy Creator");
        }
    }

    //ED-303-SA
    @Override
    public List<CreatorDTO> getCreatorsEpisode(Long mediaId) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbCreator);//ED-276-SA
        String uri = "/creator/creators-mediaid?mediaType="+MediaType.PODCAST_EPISODE+"&id=" + mediaId;
        String token = keycloak.getAccessToken();//ED-348-SA
        try{
            List<CreatorDTO> response = restClient
                    .get()
                    .uri(serviceInstance.getUri()+ "/creator/creators-mediaid?mediaType="+MediaType.PODCAST_EPISODE+"&id=" + mediaId)
                    .header("Authorization", "Bearer "+token)//ED-348-SA
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<CreatorDTO>>() {});

            if(response == null){
                throw new CallFailException("Creator", uri);
            }

            return response;

        } catch (RestClientResponseException e) {
            throw new CallFailException("Creator", uri, String.format(e.getMessage(), e));
        }catch (RestClientException e){
            throw new RestClientException("Edufy Pod", "Edufy Creator");
        }
    }

    //ED-303-SA
    @Override
    public List<CreatorDTO> getCreatorsSeason(Long mediaId) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbCreator);//ED-276-SA
        String uri = "/creator/creators-mediaid?mediaType="+MediaType.PODCAST_SEASON+"&id=" + mediaId;
        String token = keycloak.getAccessToken();//ED-348-SA
        try{
            List<CreatorDTO> response = restClient
                    .get()
                    .uri(serviceInstance.getUri()+ "/creator/creators-mediaid?mediaType="+MediaType.PODCAST_SEASON+"&id=" + mediaId)
                    .header("Authorization", "Bearer "+token)//ED-348-SA
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<CreatorDTO>>() {});

            if(response == null){
                throw new CallFailException("Creator", uri);
            }

            return response;

        } catch (RestClientResponseException e) {
            throw new CallFailException("Creator", uri, String.format(e.getMessage(), e));
        }catch (RestClientException e){
            throw new RestClientException("Edufy Pod", "Edufy Creator");
        }
    }

    //ED-232-SA
    @Override
    public void createRecordOfMedia(Long mediaId, MediaType mediaType, List<Long> creatorIds) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbCreator);
        String uri = "/creator/media/record";
        String token = keycloak.getAccessToken();//ED-348-SA

        try {
            ResponseEntity<Void> response = restClient.put()//ED-348-SA
                    .uri(serviceInstance.getUri()+uri)
                    .header("Authorization", "Bearer "+token)//ED-348-SA
                    .body(new RecordOfCreatorDTO(mediaId, mediaType, creatorIds))
                    .retrieve()
                    .toBodilessEntity();

            if(!response.getStatusCode().equals(HttpStatus.OK)){//ED-348-SA
                throw new IllegalStateException("Unexpected response status code: " + response.getStatusCode());
            }

        }catch (RestClientResponseException e){
            String error = e.getResponseBodyAsString();
            throw new InvalidInputException("Edufy Creator service error: "+e.getMessage() + "\nerror:"+error+"\nstatus:"+e.getStatusCode());//ED-348-SA
        }catch (RestClientException e){
            throw new RestClientException("Edufy Pod", "Edufy Creator");
        }

    }

    //ED-232-SA
    @Override
    public CreatorDTO getCreatorById(Long creatorId) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbCreator);
        String uri = "/creator/creator/"+creatorId+"/clientcall";
        String token = keycloak.getAccessToken();//ED-348-SA
        try {
            return restClient.get()
                    .uri(serviceInstance.getUri()+"/creator/creator/"+creatorId+"/clientcall")
                    .header("Authorization", "Bearer "+token)//ED-348-SA
                    .retrieve()
                    .body(CreatorDTO.class);
        }catch (RestClientResponseException e){
            throw new CallFailException("Creator", uri, String.format(e.getMessage(), e));
        }catch (ResourceAccessException e){
            throw new RestClientException("Edufy Pod", "Edufy Creator");
        }
    }
}
