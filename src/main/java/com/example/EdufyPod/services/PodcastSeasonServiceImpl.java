package com.example.EdufyPod.services;

import com.example.EdufyPod.clients.CreatorClient;
import com.example.EdufyPod.clients.GenreClient;
import com.example.EdufyPod.converters.Roles;
import com.example.EdufyPod.exceptions.*;
import com.example.EdufyPod.models.DTO.IncomingPodcastSeasonDTO;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.DTO.mappers.PodcastSeasonMapper;
import com.example.EdufyPod.models.entities.PodcastSeason;
import com.example.EdufyPod.models.enums.MediaType;
import com.example.EdufyPod.repositories.PodcastSeasonRepository;
import com.example.EdufyPod.services.Utility.Validate;
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

    //ED-242-SA
    @Override
    public PodcastSeasonDTO createPodcastSeason(IncomingPodcastSeasonDTO incomingPodcastSeasonDTO) {

        notNull(incomingPodcastSeasonDTO);

        Validate.validateCreators(incomingPodcastSeasonDTO.getCreatorIds(), creatorClient);

        PodcastSeason season = new PodcastSeason();
        season.setTitle(incomingPodcastSeasonDTO.getTitle());
        season.setDescription(incomingPodcastSeasonDTO.getDescription());
        season.setUrl(incomingPodcastSeasonDTO.getUrl());
        season.setReleaseDate(incomingPodcastSeasonDTO.getReleaseDate());

        season.setActive(true);

        checkValues(season);

        podcastSeasonRepository.save(season);

        creatorClient.createRecordOfMedia(season.getId(), MediaType.PODCAST_SEASON, incomingPodcastSeasonDTO.getCreatorIds());

        return PodcastSeasonMapper.toDTOAdmin(season,creatorClient, genreClient);
    }

    //ED-83-SA
    private void emptySeasonList(List<PodcastSeason> podcastSeasons) {
        if(podcastSeasons.isEmpty()){
            throw new ContentNotFoundException("No podcast seasons found");
        }
    }

    //ED-242-SA
    private void notNull(IncomingPodcastSeasonDTO incomingPodcastSeasonDTO){
        if(incomingPodcastSeasonDTO.getTitle() == null){
            throw new NullValueException("Title", incomingPodcastSeasonDTO.getTitle());
        }
        if(incomingPodcastSeasonDTO.getDescription() == null){
            throw new NullValueException("Description", incomingPodcastSeasonDTO.getDescription());
        }
        if(incomingPodcastSeasonDTO.getUrl() == null){
            throw new NullValueException("Url", incomingPodcastSeasonDTO.getUrl());
        }
        if(incomingPodcastSeasonDTO.getReleaseDate() == null){
            throw new NullValueException("ReleaseDate", incomingPodcastSeasonDTO.getReleaseDate());
        }
        if(incomingPodcastSeasonDTO.getCreatorIds() == null){
            throw new NullValueException("CreatorIds", incomingPodcastSeasonDTO.getCreatorIds());
        }
    }

    //ED-242-SA
    private void checkValues(PodcastSeason podcastSeason){
        if(podcastSeason.getTitle().isEmpty()){
            throw new NullValueException("Title", podcastSeason.getTitle());
        }
        if(podcastSeason.getTitle().length() < 3){
            throw new ValidFieldsException("Title","more than 3 characters",podcastSeason.getTitle());
        }
        if(podcastSeason.getDescription().isEmpty()){
            throw new NullValueException("Description", podcastSeason.getDescription());
        }
        if(podcastSeason.getDescription().length() < 10){
            throw new ValidFieldsException("Description","more than 10 characters",podcastSeason.getDescription());
        }
        if(podcastSeason.getUrl().isEmpty()){
            throw new NullValueException("Url", podcastSeason.getUrl());
        }
        if(!podcastSeason.getUrl().contains("http://") && !podcastSeason.getUrl().contains("https://")){
            throw new ValidFieldsException("URL", "needs to contain either http:// or https://", podcastSeason.getUrl());
        }
        if(podcastSeasonRepository.existsByUrl(podcastSeason.getUrl())){
            throw new UniqueConflictException("Url", podcastSeason.getUrl());
        }
    }
}
