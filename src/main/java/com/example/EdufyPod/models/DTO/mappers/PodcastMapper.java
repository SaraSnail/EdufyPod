package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.entities.Podcast;

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
        podcastDTO.setSeason(PodcastSeasonMapper.toDTO(podcast.getSeason()));
        podcastDTO.setTimesListened(podcast.getTimesListened());
        return podcastDTO;
    }
}
