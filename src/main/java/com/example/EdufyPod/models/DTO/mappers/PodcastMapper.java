package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;

import java.util.ArrayList;
import java.util.List;

public class PodcastMapper {

    public static PodcastDTO toDTO(Podcast podcast) {
        PodcastDTO podcastDTO = new PodcastDTO();
        podcastDTO.setTitle(podcast.getTitle());
        podcastDTO.setUrl(podcast.getUrl());
        podcastDTO.setDescription(podcast.getDescription());
        podcastDTO.setCreators(CreatorMapper.getCreators(podcast));//TODO: fix later
        podcastDTO.setReleaseDate(podcast.getReleaseDate());
        podcastDTO.setGenres(GenreMapping.getGenres(podcast));//TODO: fix later
        podcastDTO.setLength(podcast.getLength());
        podcastDTO.setNrInSeason(podcast.getNrInSeason());
        podcastDTO.setTimesListened(podcast.getTimesListened());
        return podcastDTO;
    }

    public static PodcastDTO toDTONoId(Podcast podcast) {
        PodcastDTO podcastDTO = toDTO(podcast);

        PodcastSeasonDTO seasonDTO = new PodcastSeasonDTO();
        PodcastSeason season = podcast.getSeason();
        if(season != null) {
            seasonDTO.setTitle(season.getTitle());
            seasonDTO.setUrl(season.getUrl());
            podcastDTO.setSeason(seasonDTO);
        }
        return podcastDTO;
    }

    public static PodcastDTO DTOWithId(Podcast podcast) {
        PodcastDTO podcastDTO = toDTO(podcast);
        podcastDTO.setId(podcast.getId());

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

    public static List<PodcastDTO> toDTOList(List<Podcast> podcasts) {
        List<PodcastDTO> podcastDTOS = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            podcastDTOS.add(toDTONoId(podcast));
        }
        return podcastDTOS;
    }

    public static List<PodcastDTO> DTOWithIdList(List<Podcast> podcasts) {
        List<PodcastDTO> podcastDTOS = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            podcastDTOS.add(DTOWithId(podcast));
        }
        return podcastDTOS;
    }
}
