package com.example.EdufyPod.services;

import com.example.EdufyPod.converters.Roles;
import com.example.EdufyPod.exceptions.ContentNotFoundException;
import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.models.DTO.CreatorDTO;
import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.DTO.mappers.CreatorMapper;
import com.example.EdufyPod.models.DTO.mappers.PodcastSeasonMapper;
import com.example.EdufyPod.models.entities.PodcastSeason;
import com.example.EdufyPod.repositories.PodcastSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//ED-77-SA
@Service
public class PodcastSeasonServiceImpl implements PodcastSeasonService {

    private final PodcastSeasonRepository podcastSeasonRepository;
    private final CreatorService creatorService;

    //ED-77-SA
    @Autowired
    public PodcastSeasonServiceImpl(PodcastSeasonRepository podcastSeasonRepository, CreatorService creatorService) {
        this.podcastSeasonRepository = podcastSeasonRepository;
        this.creatorService = creatorService;
    }

    //ED-77-SA
    @Override
    public PodcastSeasonDTO getPodcastSeasonById(Long id) {
        Optional<PodcastSeason> findPodcastSeason = podcastSeasonRepository.findById(id);
        if(findPodcastSeason.isEmpty()){
            throw new ResourceNotFoundException("Podcast Season", "id", id);
        }

        return mapCreatorsAdmin(findPodcastSeason.get());
    }

    //ED-58-SA
    @Override
    public List<PodcastSeasonDTO> getPodcastSeasonByTitle(String title) {
        List<PodcastSeason> podcastSeasons = podcastSeasonRepository.findAllByTitleContainingIgnoreCaseAndIsActiveTrue(title);//ED-219-SA:changed so only ones where isActive=true
        if(podcastSeasons.isEmpty()){
            throw new ResourceNotFoundException("Podcast Season", "title containing", title);
        }

        List<PodcastSeasonDTO> podcastSeasonDTOs = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOs.add(mapCreatorsUser(podcastSeason));
        }

        return podcastSeasonDTOs;
    }

    //ED-83-SA
    @Override
    public List<PodcastSeasonDTO> getAllPodcastSeasons(Authentication authentication) {
        List<PodcastSeason> allPodcastSeasons;

        List<String> roles = Roles.getRoles(authentication);

        if(roles.contains("pod_admin") || roles.contains("edufy_realm_admin")){
            allPodcastSeasons = podcastSeasonRepository.findAll();
            emptySeasonList(allPodcastSeasons);
            List<PodcastSeasonDTO> podcastSeasonDTOs = new ArrayList<>();
            for(PodcastSeason podcastSeason : allPodcastSeasons){
                podcastSeasonDTOs.add(mapCreatorsAdmin(podcastSeason));
            }
            return podcastSeasonDTOs;

        }else {
            allPodcastSeasons = podcastSeasonRepository.findAllByIsActiveTrue();
            emptySeasonList(allPodcastSeasons);
            List<PodcastSeasonDTO> podcastSeasonDTOs = new ArrayList<>();
            for(PodcastSeason podcastSeason : allPodcastSeasons){
                podcastSeasonDTOs.add(mapCreatorsUser(podcastSeason));
            }
            return podcastSeasonDTOs;

        }

    }

    //ED-83-SA
    private void emptySeasonList(List<PodcastSeason> podcastSeasons) {
        if(podcastSeasons.isEmpty()){
            throw new ContentNotFoundException("No podcast seasons found");
        }
    }

    //ED-276-SA
    private PodcastSeasonDTO mapCreatorsAdmin(PodcastSeason podcastSeason) {
        PodcastSeasonDTO podcastSeasonDTO = PodcastSeasonMapper.toDTOAdmin(podcastSeason);
        List<CreatorDTO> creatorDTOs = creatorService.getCreatorsForSeason(podcastSeason.getId());

        podcastSeasonDTO.setCreators(CreatorMapper.toDTOAdminList(creatorDTOs));

        for(PodcastDTO podcastDTO : podcastSeasonDTO.getEpisodes()){
            Long podcastId = podcastDTO.getId();
            List<CreatorDTO> creatorsPodcast = creatorService.getCreatorsForEpisode(podcastId);
            podcastDTO.setCreators(CreatorMapper.toDTOAdminList(creatorsPodcast));
        }

        return podcastSeasonDTO;
    }

    //ED-276-SA
    private PodcastSeasonDTO mapCreatorsUser(PodcastSeason podcastSeason) {
        PodcastSeasonDTO podcastSeasonDTO = PodcastSeasonMapper.toDTOUser(podcastSeason);
        List<CreatorDTO> creatorDTOs = creatorService.getCreatorsForSeason(podcastSeason.getId());

        podcastSeasonDTO.setCreators(CreatorMapper.toDTOUserList(creatorDTOs));

        for(PodcastDTO podcastDTO : podcastSeasonDTO.getEpisodes()){
            Long podcastId = podcastDTO.getId();
            List<CreatorDTO> creatorsPodcast = creatorService.getCreatorsForEpisode(podcastId);
            podcastDTO.setCreators(CreatorMapper.toDTOUserList(creatorsPodcast));
            podcastDTO.setId(null);
        }

        return podcastSeasonDTO;
    }
}
