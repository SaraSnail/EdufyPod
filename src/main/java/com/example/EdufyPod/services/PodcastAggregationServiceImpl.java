package com.example.EdufyPod.services;

import com.example.EdufyPod.clients.GenreClient;
import com.example.EdufyPod.converters.Roles;
import com.example.EdufyPod.exceptions.ContentNotFoundException;
import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.models.DTO.*;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastDTO;
import com.example.EdufyPod.models.DTO.callDTOs.TransferPodcastSeasonDTO;
import com.example.EdufyPod.models.DTO.mappers.PodcastMapper;
import com.example.EdufyPod.models.DTO.mappers.PodcastSeasonMapper;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;
import com.example.EdufyPod.repositories.PodcastRepository;
import com.example.EdufyPod.repositories.PodcastSeasonRepository;
import com.example.EdufyPod.clients.CreatorClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

//ED-60-SA
@Service
public class PodcastAggregationServiceImpl implements PodcastAggregationService {

    private final PodcastRepository podcastRepository;
    private final PodcastSeasonRepository podcastSeasonRepository;
    private final CreatorClient creatorClient;//ED-276-SA
    private final GenreClient genreClient;

    //ED-60-SA
    @Autowired
    public PodcastAggregationServiceImpl(PodcastRepository podcastRepository, PodcastSeasonRepository podcastSeasonRepository, CreatorClient creatorClient, GenreClient genreClient) {
        this.podcastRepository = podcastRepository;
        this.podcastSeasonRepository = podcastSeasonRepository;
        this.creatorClient = creatorClient;
        this.genreClient = genreClient;
    }

    //ED-60-SA : gets podcast episodes and seasons based on id. Will ignore not found id one some if the list still contains some with valid ids
    public PodcastResponse getPodcastsAndSeasonsByIds(Long creatorId, Authentication authentication) {
        //ED-310-SA
        creatorClient.getCreatorById(creatorId);

        List<PodcastSeasonDTO> seasonsDTOS = List.of();
        List<Long> missingSeasonIds = List.of();

        List<PodcastDTO> podcastDTOS = List.of();
        List<Long> missingEpisodeIds = List.of();

        //ED-303-SA
        List<TransferPodcastDTO> creatorPodcasts = creatorClient.transferPodcastDTOs(creatorId);
        List<TransferPodcastSeasonDTO> creatorPodcastSeason = creatorClient.transferPodcastSeasonDTOs(creatorId);

        if(creatorPodcasts.isEmpty() || creatorPodcastSeason.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Podcasts and Seasons not found from Creator. PodcastDTO size:"+creatorPodcasts.size()+". SeasonDTO size:"+creatorPodcastSeason.size());
        }

        //ED-303-SA
        List<Long> seasonIds = creatorPodcastSeason.stream()
                .map(TransferPodcastSeasonDTO::getMediaId)
                .filter(Objects::nonNull)
                .toList();

        List<Long> podcastIds = creatorPodcasts.stream()
                .map(TransferPodcastDTO::getMediaId)
                .filter(Objects::nonNull)
                .toList();

        //ED-303-SA
        List<String> roles = Roles.getRoles(authentication);

        if(seasonIds.isEmpty() || podcastIds.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Season id or epsiode id is empty. SeasonId size:"+seasonIds.size()+". episodeId size:"+podcastIds.size()+". PodccastDTO 1:"+creatorPodcasts.get(1).toString() + ". SeasonDTO 1:"+creatorPodcastSeason.get(1).toString());
        }

        //ED-60-SA
        if(!seasonIds.isEmpty()){
            List<PodcastSeason> seasons;

            if(roles.contains("edufy_realm_admin") || roles.contains("pod_admin")){
                seasons = podcastSeasonRepository.findAllById(seasonIds);
                emptySeasonList(seasons, seasonIds);
                seasonsDTOS = PodcastSeasonMapper.toDTONoEpisodeListAdmin(seasons, creatorClient);
            }else {
                seasons = podcastSeasonRepository.findAllByIdInAndIsActiveTrue(seasonIds);
                emptySeasonList(seasons, seasonIds);
                seasonsDTOS = PodcastSeasonMapper.toDTONoEpisodeListUser(seasons, creatorClient);
            }

            missingSeasonIds = seasonIds.stream()
                    .filter(id -> seasons.stream().noneMatch(s -> s.getId().equals(id)))
                    .toList();
        }

        if(!podcastIds.isEmpty()){

            List<Podcast> podcasts;
            if(roles.contains("edufy_realm_admin") || roles.contains("pod_admin")){
                podcasts = podcastRepository.findAllById(podcastIds);
                emptyPodcastList(podcasts, podcastIds);
                podcastDTOS = PodcastMapper.toDTOAdminList(sortList(podcasts),creatorClient, genreClient);
            }else {
                podcasts = podcastRepository.findAllByIdInAndIsActiveTrue(podcastIds);
                emptyPodcastList(podcasts, podcastIds);
                podcastDTOS = PodcastMapper.toDTOUserList(sortList(podcasts),creatorClient, genreClient);
            }

            missingEpisodeIds = podcastIds.stream()
                    .filter(id -> podcasts.stream().noneMatch(p -> p.getId().equals(id)))
                    .toList();
        }

        if(podcastDTOS.isEmpty() && seasonsDTOS.isEmpty()){
            //throw new ContentNotFoundException("No podcasts or seasons");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No podcasts or seasons");
        }

        return new PodcastResponse(seasonsDTOS, podcastDTOS, missingEpisodeIds, missingSeasonIds);
    }

    //ED-231-SA : this one only shows seasons, but the seasons also contains all their episodes
    public SeasonResponse getSeasonsByIds(Long creatorId, Authentication authentication) {
        List<PodcastSeasonDTO> seasonsDTOS = List.of();
        List<Long> missingSeasonIds = List.of();

        //ED-310-SA
        creatorClient.getCreatorById(creatorId);

        //ED-303-SA
        List<TransferPodcastSeasonDTO> seasonDTOs = creatorClient.transferPodcastSeasonDTOs(creatorId);

        //ED-303-SA
        List<Long> seasonIds = seasonDTOs.stream()
                .map(TransferPodcastSeasonDTO::getMediaId)
                .filter(Objects::nonNull)
                .toList();

        //ED-303-SA
        List<String> roles = Roles.getRoles(authentication);


        //ED-231-SA
        if(!seasonIds.isEmpty()){
            List<PodcastSeason> seasons;
            if(roles.contains("edufy_realm_admin") || roles.contains("pod_admin")){
                seasons = podcastSeasonRepository.findAllById(seasonIds);
                emptySeasonList(seasons, seasonIds);
                seasonsDTOS = PodcastSeasonMapper.toDTOAdminList(seasons,creatorClient, genreClient);
            }else {
                seasons = podcastSeasonRepository.findAllByIdInAndIsActiveTrue(seasonIds);
                emptySeasonList(seasons, seasonIds);
                seasonsDTOS = PodcastSeasonMapper.toDTOUserList(seasons,creatorClient, genreClient);
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
