package com.example.EdufyPod.services;

import com.example.EdufyPod.models.DTO.CreatorDTO;

import java.util.List;

public interface CreatorService {
    List<CreatorDTO> getCreatorsForEpisode(Long podcastId);
    List<CreatorDTO> getCreatorsForSeason(Long seasonId);
}
