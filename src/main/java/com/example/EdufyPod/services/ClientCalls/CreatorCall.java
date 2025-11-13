package com.example.EdufyPod.services.ClientCalls;

import com.example.EdufyPod.models.DTO.GenreDTO;
import com.example.EdufyPod.models.DTO.TransferPodcastDTO;
import com.example.EdufyPod.models.DTO.TransferPodcastSeasonDTO;

import java.util.List;

public interface CreatorCall {
    List<TransferPodcastDTO> transferPodcastDTOs(Long creatorId);
    List<TransferPodcastSeasonDTO> transferPodcastSeasonDTOs(Long creatorId);
    List<GenreDTO.CreatorDTO> getCreatorsEpisode(Long mediaId);
    List<GenreDTO.CreatorDTO> getCreatorsSeason(Long mediaId);
}
