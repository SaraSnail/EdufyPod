package com.example.EdufyPod.services;

import com.example.EdufyPod.models.DTO.PodcastResponse;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

//ED-303-SA
public interface PodcastAggregationService {
    PodcastResponse getPodcastsAndSeasonsByIds(Long creatorId, Authentication authentication);//ED-303-SA
    List<PodcastSeasonDTO> getSeasonsByIds(Long creatorId, Authentication authentication);//ED-303-SA //ED-377-SA: switched to List<PodcastSeasonDTO>
}
