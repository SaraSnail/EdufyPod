package com.example.EdufyPod.services;

import com.example.EdufyPod.converters.Roles;
import com.example.EdufyPod.exceptions.ContentNotFoundException;
import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.models.DTO.CreatorDTO;
import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.mappers.CreatorMapper;
import com.example.EdufyPod.models.DTO.mappers.PodcastMapper;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.repositories.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//ED-76-SA
@Service
public class PodcastServiceImpl implements PodcastService {

    private final PodcastRepository podcastRepository;
    private final CreatorService creatorService;

    //ED-76-SA
    @Autowired
    public PodcastServiceImpl(PodcastRepository podcastRepository,CreatorService creatorService) {
        this.podcastRepository = podcastRepository;
        this.creatorService = creatorService;
    }

    //ED-76-SA
    @Override
    public PodcastDTO getPodcastById(Long id) {
        Optional<Podcast> findPodcast = podcastRepository.findById(id);
        if(findPodcast.isEmpty()){
            throw new ResourceNotFoundException("Podcast", "id", id);
        }
        Podcast podcast = findPodcast.get();

        return mapToAdmin(podcast);
    }

    //ED-56-SA
    @Override
    public List<PodcastDTO> getPodcastByTitle(String title) {
        List<Podcast> podcasts = podcastRepository.findAllByTitleContainingIgnoreCaseAndIsActiveTrue(title);//ED-219-SA:changed so only ones where isActive=true
        if(podcasts.isEmpty()){
            throw new ResourceNotFoundException("Podcast", "title containing", title);
        }

        List<PodcastDTO> podcastDTOs = new ArrayList<>();
        for(Podcast podcast : podcasts){
            podcastDTOs.add(mapToUser(podcast));
        }

        return podcastDTOs;
    }

    //ED-82-SA
    @Override
    public List<PodcastDTO> getAllPodcasts(Authentication authentication) {
        List<Podcast> allPodcastEpisodes;

        List<String> roles = Roles.getRoles(authentication);//ED-83-SA

        if(roles.contains("pod_admin") || roles.contains("edufy_realm_admin")){
            allPodcastEpisodes = podcastRepository.findAll();
            listPodEmpty(allPodcastEpisodes);

            List<PodcastDTO> podcastDTOs = new ArrayList<>();
            for(Podcast podcast : allPodcastEpisodes){
                podcastDTOs.add(mapToAdmin(podcast));
            }

            return podcastDTOs;

        }else {
            allPodcastEpisodes = podcastRepository.findAllByIsActiveTrue();
            listPodEmpty(allPodcastEpisodes);

            List<PodcastDTO> podcastDTOs = new ArrayList<>();
            for(Podcast podcast : allPodcastEpisodes){
                podcastDTOs.add(mapToUser(podcast));
            }

            return podcastDTOs;
        }
    }

    //ED-82-SA
    private void listPodEmpty(List<Podcast> podcasts){
        if(podcasts.isEmpty()){
            throw new ContentNotFoundException("No podcast episodes found");
        }
    }


    //ED-276-SA
    private PodcastDTO mapToAdmin(Podcast podcast){
        PodcastDTO podcastDTO = PodcastMapper.toDTOAdmin(podcast);
        List<CreatorDTO> creatorDTOs = creatorService.getCreatorsForEpisode(podcast.getId());
        podcastDTO.setCreators(CreatorMapper.toDTOAdminList(creatorDTOs));
        return podcastDTO;
    }

    //ED-276-SA
    private PodcastDTO mapToUser(Podcast podcast){
        PodcastDTO podcastDTO = PodcastMapper.toDTOUser(podcast);
        List<CreatorDTO> creatorDTOs = creatorService.getCreatorsForEpisode(podcast.getId());
        podcastDTO.setCreators(CreatorMapper.toDTOUserList(creatorDTOs));
        podcastDTO.setId(null);
        return podcastDTO;
    }
}
