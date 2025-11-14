package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.DTO.CreatorDTO;
import com.example.EdufyPod.models.DTO.TransferPodcastDTO;
import com.example.EdufyPod.models.DTO.TransferPodcastSeasonDTO;

import java.util.List;

public interface CreatorClient {
    List<TransferPodcastDTO> transferPodcastDTOs(Long creatorId);
    List<TransferPodcastSeasonDTO> transferPodcastSeasonDTOs(Long creatorId);
    List<CreatorDTO> getCreatorsEpisode(Long mediaId);
    List<CreatorDTO> getCreatorsSeason(Long mediaId);
}
