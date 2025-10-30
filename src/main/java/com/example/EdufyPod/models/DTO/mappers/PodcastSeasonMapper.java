package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.entities.PodcastSeason;

import java.util.List;

public class PodcastSeasonMapper {

    public static PodcastSeasonDTO toDTO(PodcastSeason podcastSeason) {
        PodcastSeasonDTO podcastSeasonDTO = new PodcastSeasonDTO();
        podcastSeasonDTO.setTitle(podcastSeason.getTitle());
        podcastSeasonDTO.setUrl(podcastSeason.getUrl());
        podcastSeasonDTO.setDescription(podcastSeason.getDescription());
        podcastSeasonDTO.setCreators(CreatorMapper.getCreators(podcastSeason));//TODO: fix later
        podcastSeasonDTO.setReleaseDate(podcastSeason.getReleaseDate());
        podcastSeasonDTO.setGenres(GenreMapping.getGenres(podcastSeason));//TODO: fix later
        podcastSeasonDTO.setEpisodeCount(podcastSeason.getEpisodeCount());
        return podcastSeasonDTO;
    }
}
