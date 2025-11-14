package com.example.EdufyPod.services;

import com.example.EdufyPod.clients.CreatorClient;
import com.example.EdufyPod.clients.GenreClient;
import com.example.EdufyPod.converters.Roles;
import com.example.EdufyPod.exceptions.ContentNotFoundException;
import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.DTO.mappers.PodcastSeasonMapper;
import com.example.EdufyPod.models.entities.PodcastSeason;
import com.example.EdufyPod.repositories.PodcastSeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//ED-77-SA
@Service
public class PodcastSeasonServiceImpl implements PodcastSeasonService {

    private final PodcastSeasonRepository podcastSeasonRepository;
    private final CreatorClient creatorClient;//ED-276-SA
    private final GenreClient genreClient;//ED-267-SA

    //ED-77-SA
    @Autowired
    public PodcastSeasonServiceImpl(PodcastSeasonRepository podcastSeasonRepository, CreatorClient creatorClient, GenreClient genreClient) {
        this.podcastSeasonRepository = podcastSeasonRepository;
        this.creatorClient = creatorClient;
        this.genreClient = genreClient;
    }

    //ED-77-SA
    @Override
    public PodcastSeasonDTO getPodcastSeasonById(Long id) {
        Optional<PodcastSeason> findPodcastSeason = podcastSeasonRepository.findById(id);
        if(findPodcastSeason.isEmpty()){
            throw new ResourceNotFoundException("Podcast Season", "id", id);
        }
        return PodcastSeasonMapper.toDTOAdmin(findPodcastSeason.get(),creatorClient, genreClient);
    }

    //ED-58-SA
    @Override
    public List<PodcastSeasonDTO> getPodcastSeasonByTitle(String title) {
        List<PodcastSeason> podcastSeasons = podcastSeasonRepository.findAllByTitleContainingIgnoreCaseAndIsActiveTrue(title);//ED-219-SA:changed so only ones where isActive=true
        if(podcastSeasons.isEmpty()){
            throw new ResourceNotFoundException("Podcast Season", "title containing", title);
        }
        return PodcastSeasonMapper.toDTOUserList(podcastSeasons,creatorClient, genreClient);
    }

    //ED-83-SA
    @Override
    public List<PodcastSeasonDTO> getAllPodcastSeasons(Authentication authentication) {
        List<PodcastSeason> allPodcastSeasons;

        List<String> roles = Roles.getRoles(authentication);

        if(roles.contains("pod_admin") || roles.contains("edufy_realm_admin")){
            allPodcastSeasons = podcastSeasonRepository.findAll();
            emptySeasonList(allPodcastSeasons);
            return PodcastSeasonMapper.toDTOAdminList(allPodcastSeasons,creatorClient, genreClient);

        }else {
            allPodcastSeasons = podcastSeasonRepository.findAllByIsActiveTrue();
            emptySeasonList(allPodcastSeasons);
            return PodcastSeasonMapper.toDTOUserList(allPodcastSeasons,creatorClient, genreClient);

        }

    }

    //ED-83-SA
    private void emptySeasonList(List<PodcastSeason> podcastSeasons) {
        if(podcastSeasons.isEmpty()){
            throw new ContentNotFoundException("No podcast seasons found");
        }
    }
}
