package com.example.EdufyPod.services;

import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastResponse;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.DTO.mappers.PodcastMapper;
import com.example.EdufyPod.models.DTO.mappers.PodcastSeasonMapper;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;
import com.example.EdufyPod.repositories.PodcastRepository;
import com.example.EdufyPod.repositories.PodcastSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PodcastAggregationService {

    private final PodcastRepository podcastRepository;
    private final PodcastSeasonRepository podcastSeasonRepository;

    @Autowired
    public PodcastAggregationService(PodcastRepository podcastRepository, PodcastSeasonRepository podcastSeasonRepository) {
        this.podcastRepository = podcastRepository;
        this.podcastSeasonRepository = podcastSeasonRepository;
    }

    public PodcastResponse getPodcastsAndSeasonsByIds(List<Long> podcastIds, List<Long> seasonIds) {

        List<Podcast> podcasts = podcastRepository.findAllById(podcastIds);
        if(podcasts.isEmpty()){
            throw new ResourceNotFoundException("Podcast episodes","ids", podcastIds);
        }

        List<PodcastSeason> seasons = podcastSeasonRepository.findAllById(seasonIds);
        if(seasons.isEmpty()){
            throw new ResourceNotFoundException("Podcast seasons","ids", seasonIds);
        }

        List<PodcastDTO> podcastDTOS = PodcastMapper.toDTOWithIdList(podcasts);
        List<PodcastSeasonDTO> seasonsDTOS = PodcastSeasonMapper.toDTONoEpisodeList(seasons);//TODO: should it give season alone or with episodes?

        return new PodcastResponse(podcastDTOS, seasonsDTOS);
    }
}
