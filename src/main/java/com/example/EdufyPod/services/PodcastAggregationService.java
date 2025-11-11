package com.example.EdufyPod.services;

import com.example.EdufyPod.exceptions.ContentNotFoundException;
import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.models.DTO.*;
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
    public PodcastResponse getPodcastsAndSeasonsByIds(List<TransferPodcastSeasonDTO> seasonDTOs, List<TransferPodcastDTO> podcastDTOs, Boolean withId) {

        List<PodcastSeasonDTO> seasonsDTOS = List.of();
        List<Long> missingSeasonIds = List.of();

        List<PodcastDTO> podcastDTOS = List.of();
        List<Long> missingEpisodeIds = List.of();

        List<Long> seasonIds = seasonDTOs.stream()
                .map(TransferPodcastSeasonDTO::getId)
                .filter(Objects::nonNull)
                .toList();

        List<Long> podcastIds = podcastDTOs.stream()
                .map(TransferPodcastDTO::getId)
                .filter(Objects::nonNull)
                .toList();


        if(!seasonIds.isEmpty()){
            List<PodcastSeason> seasons;
            if(withId){
                seasons = podcastSeasonRepository.findAllById(seasonIds);
                emptySeasonList(seasons, seasonIds);
                seasonsDTOS = PodcastSeasonMapper.toDTONoEpisodeListId(seasons);
            }else {
                seasons = podcastSeasonRepository.findAllByIdInAndIsActiveTrue(seasonIds);
                emptySeasonList(seasons, seasonIds);
                seasonsDTOS = PodcastSeasonMapper.toDTONoEpisodeListNoId(seasons);
            }

            missingSeasonIds = seasonIds.stream()
                    .filter(id -> seasons.stream().noneMatch(s -> s.getId().equals(id)))
                    .toList();
        }

        if(!podcastIds.isEmpty()){

            List<Podcast> podcasts;
            if(withId){
                podcasts = podcastRepository.findAllById(podcastIds);
                emptyPodcastList(podcasts, podcastIds);
                podcastDTOS = PodcastMapper.toDTOWithIdList(sortList(podcasts));
            }else {
                podcasts = podcastRepository.findAllByIdInAndIsActiveTrue(podcastIds);
                emptyPodcastList(podcasts, podcastIds);
                podcastDTOS = PodcastMapper.toDTONoIdList(sortList(podcasts));
            }

            missingEpisodeIds = podcastIds.stream()
                    .filter(id -> podcasts.stream().noneMatch(p -> p.getId().equals(id)))
                    .toList();
        }

        if(podcastDTOS.isEmpty() && seasonsDTOS.isEmpty()){
            throw new ContentNotFoundException("No podcasts or seasons");
        }

        return new PodcastResponse(seasonsDTOS, podcastDTOS, missingEpisodeIds, missingSeasonIds);
    }

    //ED-231-SA : this one only shows seasons, but the seasons also contains all their episodes
    public SeasonResponse getSeasonsByIds(List<TransferPodcastSeasonDTO> seasonDTOS, Boolean withId) {
        List<PodcastSeasonDTO> seasonsDTOS = List.of();
        List<Long> missingSeasonIds = List.of();

        List<Long> seasonIds = seasonDTOS.stream()
                .map(TransferPodcastSeasonDTO::getId)
                .filter(Objects::nonNull)
                .toList();

        if(!seasonIds.isEmpty()){
            List<PodcastSeason> seasons;
            if(withId){
                seasons = podcastSeasonRepository.findAllById(seasonIds);
                emptySeasonList(seasons, seasonIds);
                seasonsDTOS = PodcastSeasonMapper.toDTOWithIdList(seasons);
            }else {
                seasons = podcastSeasonRepository.findAllByIdInAndIsActiveTrue(seasonIds);
                emptySeasonList(seasons, seasonIds);
                seasonsDTOS = PodcastSeasonMapper.toDTONoIdList(seasons);
            }

            missingSeasonIds = seasonIds.stream()
                    .filter(id -> seasons.stream().noneMatch(s -> s.getId().equals(id)))
                    .toList();
        }

        if(seasonsDTOS.isEmpty()){
            throw new ContentNotFoundException("No seasons");
        }

        return new SeasonResponse( missingSeasonIds, seasonsDTOS);
    }

    //ED-60-SA
    private List<Podcast> sortList(List<Podcast> podcasts){
        return podcasts.stream()
                .sorted(Comparator
                        .comparing((Podcast p) -> p.getSeason().getId())
                        .thenComparing(Podcast::getNrInSeason))
                .toList();
    }

    //ED-60-SA
    private void emptyPodcastList(List<Podcast> podcasts, List<Long> podcastIds){
        if(podcasts.isEmpty()){
            throw new ResourceNotFoundException("Podcast episodes","ids", podcastIds);
        }
    }

    //ED-60-SA
    private void emptySeasonList(List<PodcastSeason> seasons, List<Long> seasonsIds){
        if(seasons.isEmpty()){
            throw new ResourceNotFoundException("Podcast seasons","ids", seasonsIds);
        }
    }

}
