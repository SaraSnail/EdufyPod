package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.DTO.callDTOs.CreatorDTO;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastDTO;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastSeasonDTO;
import com.example.EdufyPod.models.enums.MediaType;

import java.util.List;

//SA
public interface CreatorClient {
    List<TransferPodcastDTO> transferPodcastDTOs(Long creatorId);//SA
    List<TransferPodcastSeasonDTO> transferPodcastSeasonDTOs(Long creatorId);//SA
    List<CreatorDTO> getCreatorsEpisode(Long mediaId);//SA
    List<CreatorDTO> getCreatorsSeason(Long mediaId);//SA

    void createRecordOfMedia(Long mediaId, MediaType mediaType, List<Long> creatorId);//SA

    CreatorDTO getCreatorById(Long creatorId);//SA
}
