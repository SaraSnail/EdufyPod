package com.example.EdufyPod.services.ClientCalls;

import com.example.EdufyPod.exceptions.CallFailException;
import com.example.EdufyPod.models.DTO.CreatorDTO;
import com.example.EdufyPod.models.DTO.GenreDTO;
import com.example.EdufyPod.models.DTO.TransferPodcastDTO;
import com.example.EdufyPod.models.DTO.TransferPodcastSeasonDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

//ED-303-SA
@Service
public class CreatorCallImpl implements CreatorCall {

    private final RestClient restClient;

    public CreatorCallImpl() {
        this.restClient = RestClient.create();
    }

    //ED-303-SA
    @Override
    public List<TransferPodcastDTO> transferPodcastDTOs(Long creatorId) {
        String uri = "http://localhost:4545/api/v1/creator/media-creator/" + creatorId + "/POD_EPISODE";

        try{
            List<TransferPodcastDTO> response = restClient
                    .get()
                    .uri(uri)
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
        String uri = "http://localhost:4545/api/v1/creator/media-creator/" + creatorId + "/POD_SEASON";

        try{
            List<TransferPodcastSeasonDTO> response = restClient
                    .get()
                    .uri(uri)
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
        String uri = "http://localhost:4545/api/v1/creator/creatortomedia/" + mediaId + "/POD_EPISODE";
        try{
            List<CreatorDTO> response = restClient
                    .get()
                    .uri(uri)
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
        String uri = "http://localhost:4545/api/v1/creator/creatortomedia/" + mediaId + "/POD_SEASON";
        try{
            List<CreatorDTO> response = restClient
                    .get()
                    .uri(uri)
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
}
