package com.example.EdufyPod.services;

import com.example.EdufyPod.clients.CreatorClient;
import com.example.EdufyPod.clients.GenreClient;
import com.example.EdufyPod.clients.ThumbClient;
import com.example.EdufyPod.converters.DurationConverter;
import com.example.EdufyPod.converters.Roles;
import com.example.EdufyPod.exceptions.ContentNotFoundException;
import com.example.EdufyPod.exceptions.NullValueException;
import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.exceptions.ValidFieldsException;
import com.example.EdufyPod.models.DTO.*;
import com.example.EdufyPod.models.DTO.body.CreatorBody;
import com.example.EdufyPod.models.DTO.body.GenreBody;
import com.example.EdufyPod.models.DTO.body.ThumbBody;
import com.example.EdufyPod.models.DTO.callDTOs.IncomingPodcastDTO;
import com.example.EdufyPod.models.DTO.mappers.PodcastMapper;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.repositories.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

//ED-76-SA
@Service
public class PodcastServiceImpl implements PodcastService {

    private final PodcastRepository podcastRepository;
    private final CreatorClient creatorClient;//ED-276-SA
    private final GenreClient genreClient;//ED-267-SA
    private final ThumbClient thumbClient;

    //ED-76-SA
    @Autowired
    public PodcastServiceImpl(PodcastRepository podcastRepository, CreatorClient creatorClient, GenreClient genreClient, ThumbClient thumbClient) {
        this.podcastRepository = podcastRepository;
        this.creatorClient = creatorClient;
        this.genreClient = genreClient;
        this.thumbClient = thumbClient;
    }

    //ED-76-SA
    @Override
    public PodcastDTO getPodcastById(Long id) {
        Optional<Podcast> findPodcast = podcastRepository.findById(id);
        if(findPodcast.isEmpty()){
            throw new ResourceNotFoundException("Podcast", "id", id);
        }
        Podcast podcast = findPodcast.get();
        return PodcastMapper.toDTOAdmin(podcast,creatorClient, genreClient);
    }

    //ED-56-SA
    @Override
    public List<PodcastDTO> getPodcastByTitle(String title) {
        List<Podcast> podcasts = podcastRepository.findAllByTitleContainingIgnoreCaseAndIsActiveTrue(title);//ED-219-SA:changed so only ones where isActive=true
        if(podcasts.isEmpty()){
            throw new ResourceNotFoundException("Podcast", "title containing", title);
        }
        return PodcastMapper.toDTOUserList(podcasts,creatorClient, genreClient);
    }

    //ED-82-SA
    @Override
    public List<PodcastDTO> getAllPodcasts(Authentication authentication) {
        List<Podcast> allPodcastEpisodes;

        List<String> roles = Roles.getRoles(authentication);//ED-83-SA

        if(roles.contains("pod_admin") || roles.contains("edufy_realm_admin")){
            allPodcastEpisodes = podcastRepository.findAll();
            listPodEmpty(allPodcastEpisodes);
            return PodcastMapper.toDTOAdminList(allPodcastEpisodes,creatorClient, genreClient);

        }else {
            allPodcastEpisodes = podcastRepository.findAllByIsActiveTrue();
            listPodEmpty(allPodcastEpisodes);
            return PodcastMapper.toDTOUserList(allPodcastEpisodes,creatorClient, genreClient);
        }
    }

    //ED-232-SA
    @Override
    public PodcastDTO createPodcast(IncomingPodcastDTO incomingPodcastDTO) {

        notNull(incomingPodcastDTO);

        Podcast newPodcast = new Podcast();
        newPodcast.setTitle(incomingPodcastDTO.getTitle());
        newPodcast.setUrl(incomingPodcastDTO.getUrl());
        newPodcast.setDescription(incomingPodcastDTO.getDescription());
        newPodcast.setReleaseDate(incomingPodcastDTO.getReleaseDate());

        newPodcast.setLength(DurationConverter.parsePodcastDuration(incomingPodcastDTO.getLength()));

        newPodcast.setNrInSeason(incomingPodcastDTO.getNrInSeason());
        newPodcast.setTimesListened(incomingPodcastDTO.getTimesListened());

        checkValues(newPodcast);

        podcastRepository.save(newPodcast);
        Long podId = newPodcast.getId();

        String mediaType = "PODCAST_EPISODE";

        //register with creator
        CreatorBody creatorBody = new CreatorBody(podId, mediaType, incomingPodcastDTO.getCreatorIds());
        creatorClient.registerWithCreator(creatorBody);

        //register with genre
        GenreBody genreBody = new GenreBody(podId, mediaType,incomingPodcastDTO.getGenreIds());
        genreClient.registerWithGenre(genreBody);

        //register with thumb
        ThumbBody thumbBody = new ThumbBody(podId, mediaType, incomingPodcastDTO.getTitle());
        thumbClient.registerWithThumb(thumbBody);

        return PodcastMapper.toDTOAdmin(newPodcast,creatorClient, genreClient);
    }

    //ED-82-SA
    private void listPodEmpty(List<Podcast> podcasts){
        if(podcasts.isEmpty()){
            throw new ContentNotFoundException("No podcast episodes found");
        }
    }

    //ED-232-SA
    private void notNull(IncomingPodcastDTO incomingPodcastDTO){
        if(incomingPodcastDTO.getTitle() == null){
            throw new NullValueException("Title", incomingPodcastDTO.getTitle());
        }
        if(incomingPodcastDTO.getUrl() == null){
            throw new NullValueException("URL", incomingPodcastDTO.getUrl());
        }
        if(incomingPodcastDTO.getDescription() == null){
            throw new NullValueException("Description", incomingPodcastDTO.getDescription());
        }
        if(incomingPodcastDTO.getCreatorIds() == null){
            throw new NullValueException("CreatorIds", incomingPodcastDTO.getCreatorIds());
        }
        if(incomingPodcastDTO.getReleaseDate() == null){
            throw new NullValueException("ReleaseDate", incomingPodcastDTO.getReleaseDate());
        }
        if(incomingPodcastDTO.getGenreIds() == null){
            throw new NullValueException("GenreIds", incomingPodcastDTO.getGenreIds());
        }
        if(incomingPodcastDTO.getLength() == null){
            throw new NullValueException("Length", incomingPodcastDTO.getLength());
        }
        if(incomingPodcastDTO.getNrInSeason() == null){
            throw new NullValueException("NrInSeason", incomingPodcastDTO.getNrInSeason());
        }
        if(incomingPodcastDTO.getSeasonId() == null){
            throw new NullValueException("SeasonId", incomingPodcastDTO.getSeasonId());
        }
        if(incomingPodcastDTO.getTimesListened() == null){
            throw new NullValueException("TimesListened", incomingPodcastDTO.getTimesListened());
        }
    }

    //ED-232-SA
    private void checkValues(Podcast podcast){
        if(podcast.getTitle().isEmpty()){
            throw new NullValueException("Title", podcast.getTitle());
        }
        if(podcast.getTitle().length() < 3){
            throw new ValidFieldsException("Title","more than 3 characters", podcast.getTitle());
        }

        if(podcast.getUrl().isEmpty()){
            throw new NullValueException("URL", podcast.getUrl());
        }
        if(!podcast.getUrl().contains("http://") || !podcast.getUrl().contains("https://")){
            throw new ValidFieldsException("URL", "needs to contain either http:// or https://", podcast.getUrl());
        }
        if(podcast.getUrl().length() < 10){
            throw new ValidFieldsException("URL", "more than 10 characters", podcast.getUrl());
        }

        if(podcast.getDescription().isEmpty()){
            throw new NullValueException("Description", podcast.getDescription());
        }
        if(podcast.getDescription().length() < 10){
            throw new ValidFieldsException("Description", "more than 10 characters", podcast.getDescription());
        }
    }
}
