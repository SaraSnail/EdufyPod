package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.entities.PodcastSeason;

import java.util.ArrayList;
import java.util.List;

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
        podcastSeasonDTO.setActive(String.valueOf(podcastSeason.isActive()));

        podcastSeasonDTO.setEpisodes(PodcastMapper.toDTOWithIdList(podcastSeason.getPodcasts()));
        return podcastSeasonDTO;
    }

    //ED-58-SA
    public static List<PodcastSeasonDTO> toDTOWithIdList(List<PodcastSeason> podcastSeasons) {
        List<PodcastSeasonDTO> podcastSeasonDTOS = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOS.add(toDTOWithId(podcastSeason));
        }
        return podcastSeasonDTOS;
    }

    //ED-58-SA
    public static List<PodcastSeasonDTO> toDTONoIdList(List<PodcastSeason> podcastSeasons) {
        List<PodcastSeasonDTO> podcastSeasonDTOS = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOS.add(toDTONoId(podcastSeason));
        }
        return podcastSeasonDTOS;
    }

    //ED-60-SA
    public static List<PodcastSeasonDTO> toDTONoEpisodeList(List<PodcastSeason> podcastSeasons) {
        List<PodcastSeasonDTO> podcastSeasonDTOS = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOS.add(toDTONoEpisode(podcastSeason));
        }
        return podcastSeasonDTOS;
    }

    //ED-60-SA
    public static PodcastSeasonDTO toDTONoEpisode(PodcastSeason podcastSeason) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setId(podcastSeason.getId());
        podcastSeasonDTO.setActive(String.valueOf(podcastSeason.isActive()));

        return podcastSeasonDTO;
    }
}
