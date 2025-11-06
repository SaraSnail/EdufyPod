package com.example.EdufyPod.services;

import com.example.EdufyPod.exceptions.ContentNotFoundException;
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

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

//ED-60-SA
@Service
public class PodcastAggregationService {

    private final PodcastRepository podcastRepository;
    private final PodcastSeasonRepository podcastSeasonRepository;

    //ED-60-SA
    @Autowired
    public PodcastAggregationService(PodcastRepository podcastRepository, PodcastSeasonRepository podcastSeasonRepository) {
        this.podcastRepository = podcastRepository;
        this.podcastSeasonRepository = podcastSeasonRepository;
    }

    //ED-60-SA : gets podcast episodes and seasons based on id. Will ignore not found id one some if the list still contains some with valid ids
    public PodcastResponse getPodcastsAndSeasonsByIds(List<Long> seasonIds, List<Long> podcastIds) {

        List<PodcastSeasonDTO> seasonsDTOS = List.of();
        List<Long> missingSeasonIds = List.of();

        List<PodcastDTO> podcastDTOS = List.of();
        List<Long> missingEpisodeIds = List.of();


        if(!seasonIds.isEmpty()){
            var seasons = podcastSeasonRepository.findAllById(seasonIds);
            if(seasons.isEmpty()){
                throw new ResourceNotFoundException("Podcast seasons","ids", seasonIds);
            }

            seasonsDTOS = PodcastSeasonMapper.toDTONoEpisodeList(seasons);
            missingSeasonIds = seasonIds.stream()
                    .filter(id -> seasons.stream().noneMatch(s -> s.getId().equals(id)))
                    .toList();
        }

        if(!podcastIds.isEmpty()){

            var podcasts = podcastRepository.findAllById(podcastIds);
            if(podcasts.isEmpty()){
                throw new ResourceNotFoundException("Podcast episodes","ids", podcastIds);
            }

            List<Podcast> sorted = podcasts.stream()
                    .sorted(Comparator
                            .comparing((Podcast p) -> p.getSeason().getId())
                            .thenComparing(Podcast::getNrInSeason))
                    .toList();

            podcastDTOS = PodcastMapper.toDTOWithIdList(sorted);
            missingEpisodeIds = podcastIds.stream()
                    .filter(id -> podcasts.stream().noneMatch(p -> p.getId().equals(id)))
                    .toList();
        }

        if(podcastDTOS.isEmpty() && seasonsDTOS.isEmpty()){
            throw new ContentNotFoundException("No podcasts or seasons");
        }

        return new PodcastResponse(seasonsDTOS, podcastDTOS, missingEpisodeIds, missingSeasonIds);
    }
}
