package com.example.EdufyPod.services;

import com.example.EdufyPod.models.DTO.PodcastResponse;
import com.example.EdufyPod.models.DTO.SeasonResponse;
import org.springframework.security.core.Authentication;

//ED-303-SA
public interface PodcastAggregationService {
    PodcastResponse getPodcastsAndSeasonsByIds(Long creatorId, Authentication authentication);//ED-303-SA
    SeasonResponse getSeasonsByIds(Long creatorId, Authentication authentication);//ED-303-SA
}
