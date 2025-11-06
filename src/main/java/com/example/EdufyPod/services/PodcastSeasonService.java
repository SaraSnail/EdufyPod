package com.example.EdufyPod.services;

import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import org.springframework.security.core.Authentication;

import java.util.List;

//ED-77-SA
public interface PodcastSeasonService {

    PodcastSeasonDTO getPodcastSeasonById(Long id);//ED-77-SA
    List<PodcastSeasonDTO> getPodcastSeasonByTitle(String title);//ED-58-SA
    List<PodcastSeasonDTO> getAllPodcastSeasons(Authentication authentication);//ED-83-SA
}
