package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.entities.Podcast;
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
        podcastSeasonDTO.setReleaseDate(podcastSeason.getReleaseDate());
        podcastSeasonDTO.setEpisodeCount(podcastSeason.getEpisodeCount());

        return podcastSeasonDTO;
    }

    //ED-77-SA
    public static PodcastSeasonDTO toDTONoId(PodcastSeason podcastSeason) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setCreators(CreatorMapper.getCreatorsSeasonUser(podcastSeason));//TODO: fix later
        podcastSeasonDTO.setEpisodes(PodcastMapper.toDTONoIdList(podcastSeason.getPodcasts()));

        //ED-290-SA
        List<PodcastDTO> activeEpisodes = podcastSeason.getPodcasts().stream()
                .filter(Podcast::isActive)
                .map(PodcastMapper::toDTONoId)
                .toList();

        //ED-77-SA
        podcastSeasonDTO.setEpisodes(activeEpisodes);
        return podcastSeasonDTO;
    }

    //ED-77-SA
    public static PodcastSeasonDTO toDTOWithId(PodcastSeason podcastSeason) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setId(podcastSeason.getId());
        podcastSeasonDTO.setActive(podcastSeason.isActive());
        podcastSeasonDTO.setCreators(CreatorMapper.getCreatorsSeasonAdmin(podcastSeason));//TODO: fix later

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
    public static List<PodcastSeasonDTO> toDTONoEpisodeListId(List<PodcastSeason> podcastSeasons) {
        List<PodcastSeasonDTO> podcastSeasonDTOS = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOS.add(toDTONoEpisodeId(podcastSeason));
        }
        return podcastSeasonDTOS;
    }

    //ED-60-SA
    public static PodcastSeasonDTO toDTONoEpisodeId(PodcastSeason podcastSeason) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setId(podcastSeason.getId());
        podcastSeasonDTO.setActive(podcastSeason.isActive());

        return podcastSeasonDTO;
    }

    //ED-60-SA
    public static List<PodcastSeasonDTO> toDTONoEpisodeListNoId(List<PodcastSeason> podcastSeasons) {
        List<PodcastSeasonDTO> podcastSeasonDTOS = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOS.add(toDTO(podcastSeason));
        }
        return podcastSeasonDTOS;
    }
}
