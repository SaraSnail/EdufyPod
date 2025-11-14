package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.DTO.body.CreatorBody;
import com.example.EdufyPod.models.DTO.callDTOs.CreatorDTO;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastDTO;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastSeasonDTO;

import java.util.List;

public interface CreatorClient {
    List<TransferPodcastDTO> transferPodcastDTOs(Long creatorId);
    List<TransferPodcastSeasonDTO> transferPodcastSeasonDTOs(Long creatorId);
    List<CreatorDTO> getCreatorsEpisode(Long mediaId);
    List<CreatorDTO> getCreatorsSeason(Long mediaId);

    void registerWithCreator(CreatorBody body);
}
