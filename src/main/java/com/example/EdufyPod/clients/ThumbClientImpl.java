package com.example.EdufyPod.clients;

import com.example.EdufyPod.exceptions.InvalidInputException;
import com.example.EdufyPod.exceptions.RestClientException;
import com.example.EdufyPod.models.DTO.recordOfMedia.RecordOfThumbDTO;
import com.example.EdufyPod.models.enums.MediaType;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

//ED-232-SA
@Service
public class ThumbClientImpl implements ThumbClient {

    private final String lbThumb = "EDUFYTHUMB";
    private final RestClient restClient;
    private final LoadBalancerClient loadBalancer;//ED-232-SA

    public ThumbClientImpl(RestClient.Builder restClientBuilder, LoadBalancerClient loadBalancerClient) {
        this.restClient = restClientBuilder.build();
        this.loadBalancer = loadBalancerClient;
    }

    //ED-232-SA
    @Override
    public void createRecordOfMedia(Long mediaId, MediaType mediaType, String mediaName) {
        ServiceInstance serviceInstance = loadBalancer.choose(lbThumb);
        String uri = "/thumb/media/record";
        RecordOfThumbDTO recordOfThumbDTO = new RecordOfThumbDTO(mediaId, mediaType, mediaName);//ED-348-SA
        ResponseEntity<Void> response;//ED-348-SA
        try{
            response = restClient.post()
                    .uri(serviceInstance.getUri()+uri)
                    .body(new RecordOfThumbDTO(mediaId, mediaType, mediaName))
                    .retrieve()
                    .toBodilessEntity();

            if(!response.getStatusCode().equals(HttpStatus.CREATED)){
                throw new IllegalStateException("Unexpected response status code: " + response.getStatusCode());
            }

        }catch (RestClientResponseException ex){
            String error = ex.getResponseBodyAsString();
            throw new InvalidInputException("Edufy Thumb service error: "+ex.getMessage() +//ED-348-SA
                    "\n recordOfThumbBody: mediaId["+recordOfThumbDTO.getMediaId()+"]. " +
                    "MediaType["+recordOfThumbDTO.getMediaType()+"]. " +
                    "MediaName["+recordOfThumbDTO.getMediaName()+"].  " +
                    "\nerror:"+error +
                    "stauscode:"+ex.getStatusCode());

        } catch (ResourceAccessException ex){
            throw new RestClientException("Edufy Pod","Edufy Thumb");
        }

    }
}
