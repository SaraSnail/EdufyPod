package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.entities.PodcastSeason;

public class PodcastSeasonMapper {

    //ED-76-SA
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

    //ED-77-SA
    public static PodcastSeasonDTO toDTONoId(PodcastSeason podcastSeason) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setEpisodes(PodcastMapper.toDTONoIdList(podcastSeason.getPodcasts()));
        return podcastSeasonDTO;
    }

    //ED-77-SA
    public static PodcastSeasonDTO toDTOWithId(PodcastSeason podcastSeason) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setId(podcastSeason.getId());

        podcastSeasonDTO.setEpisodes(PodcastMapper.toDTOWithIdList(podcastSeason.getPodcasts()));
        return podcastSeasonDTO;
    }
}
