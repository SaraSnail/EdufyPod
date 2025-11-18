package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.clients.CreatorClient;
import com.example.EdufyPod.clients.GenreClient;
import com.example.EdufyPod.converters.DurationConverter;
import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;

import java.util.ArrayList;
import java.util.List;

//ED-76-SA
public class PodcastMapper {

    //ED-76-SA
    public static PodcastDTO toDTO(Podcast podcast) {
        PodcastDTO podcastDTO = new PodcastDTO();
        podcastDTO.setTitle(podcast.getTitle());
        podcastDTO.setUrl(podcast.getUrl());
        podcastDTO.setDescription(podcast.getDescription());
        podcastDTO.setReleaseDate(podcast.getReleaseDate());

        podcastDTO.setLength(DurationConverter.formatPodcastDuration(podcast.getLength()));
        podcastDTO.setNrInSeason(podcast.getNrInSeason());
        podcastDTO.setTimesListened(podcast.getTimesListened());
        return podcastDTO;
    }

    //ED-77-SA
    public static PodcastDTO toDTOUser(Podcast podcast, CreatorClient creatorClient, GenreClient genreClient) {
        PodcastDTO podcastDTO = toDTO(podcast);

        podcastDTO.setCreators(CreatorMapper.getCreatorsPodcastUser(podcast, creatorClient));//TODO: fix later
        podcastDTO.setGenres(GenreMapper.getGenresUser(podcast, genreClient));

        PodcastSeasonDTO seasonDTO = new PodcastSeasonDTO();
        PodcastSeason season = podcast.getSeason();
        if(season != null) {
            seasonDTO.setTitle(season.getTitle());
            seasonDTO.setUrl(season.getUrl());
            podcastDTO.setSeason(seasonDTO);
        }
        return podcastDTO;
    }

    //ED-77-SA
    public static PodcastDTO toDTOAdmin(Podcast podcast, CreatorClient creatorClient, GenreClient genreClient) {
        PodcastDTO podcastDTO = toDTO(podcast);
        podcastDTO.setId(podcast.getId());
        podcastDTO.setActive(podcast.isActive());//ED-82-SA

        podcastDTO.setCreators(CreatorMapper.getCreatorsPodcastAdmin(podcast, creatorClient));//TODO: fix later
        podcastDTO.setGenres(GenreMapper.getGenresAdmin(podcast, genreClient));


        PodcastSeasonDTO seasonDTO = new PodcastSeasonDTO();
        PodcastSeason season = podcast.getSeason();
        if(season != null) {
            seasonDTO.setId(season.getId());
            seasonDTO.setTitle(season.getTitle());
            seasonDTO.setUrl(season.getUrl());
            podcastDTO.setSeason(seasonDTO);
        }

        return podcastDTO;
    }

    //ED-77-SA
    public static List<PodcastDTO> toDTOUserList(List<Podcast> podcasts, CreatorClient creatorClient, GenreClient genreClient) {
        List<PodcastDTO> podcastDTOS = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            podcastDTOS.add(toDTOUser(podcast, creatorClient, genreClient));
        }
        return podcastDTOS;
    }

    //ED-77-SA
    public static List<PodcastDTO> toDTOAdminList(List<Podcast> podcasts, CreatorClient creatorClient, GenreClient genreClient) {
        List<PodcastDTO> podcastDTOS = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            podcastDTOS.add(toDTOAdmin(podcast, creatorClient, genreClient));
        }
        return podcastDTOS;
    }

    //ED-283-SA
    public static List<PodcastDTO> toDTOOnlyId(List<Podcast> podcasts){
        List<PodcastDTO> podcastDTOS = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            PodcastDTO podcastDTO = new PodcastDTO();
            podcastDTO.setId(podcast.getId());
            podcastDTOS.add(podcastDTO);
        }
        return podcastDTOS;
    }
}
