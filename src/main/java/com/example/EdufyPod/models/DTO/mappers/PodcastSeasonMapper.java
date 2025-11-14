package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.clients.CreatorClient;
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
    public static PodcastSeasonDTO toDTOUser(PodcastSeason podcastSeason, CreatorClient creatorClient) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setCreators(CreatorMapper.getCreatorsSeasonUser(podcastSeason, creatorClient));//TODO: fix later
        podcastSeasonDTO.setEpisodes(PodcastMapper.toDTOUserList(podcastSeason.getPodcasts(), creatorClient));

        List<PodcastDTO> activeEpisodes = new ArrayList<>();
        for(Podcast podEp : podcastSeason.getPodcasts()) {
            if(podEp.isActive()){
                activeEpisodes.add(PodcastMapper.toDTOUser(podEp, creatorClient));
            }
        }

        //ED-77-SA
        podcastSeasonDTO.setEpisodes(activeEpisodes);
        return podcastSeasonDTO;
    }

    //ED-77-SA
    public static PodcastSeasonDTO toDTOAdmin(PodcastSeason podcastSeason, CreatorClient creatorClient) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setId(podcastSeason.getId());
        podcastSeasonDTO.setActive(podcastSeason.isActive());
        podcastSeasonDTO.setCreators(CreatorMapper.getCreatorsSeasonAdmin(podcastSeason, creatorClient));//TODO: fix later

        podcastSeasonDTO.setEpisodes(PodcastMapper.toDTOAdminList(podcastSeason.getPodcasts(), creatorClient));
        return podcastSeasonDTO;
    }

    //ED-58-SA
    public static List<PodcastSeasonDTO> toDTOAdminList(List<PodcastSeason> podcastSeasons, CreatorClient creatorClient) {
        List<PodcastSeasonDTO> podcastSeasonDTOS = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOS.add(toDTOAdmin(podcastSeason, creatorClient));
        }
        return podcastSeasonDTOS;
    }

    //ED-58-SA
    public static List<PodcastSeasonDTO> toDTOUserList(List<PodcastSeason> podcastSeasons, CreatorClient creatorClient) {
        List<PodcastSeasonDTO> podcastSeasonDTOS = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOS.add(toDTOUser(podcastSeason, creatorClient));
        }
        return podcastSeasonDTOS;
    }

    //ED-60-SA
    public static List<PodcastSeasonDTO> toDTONoEpisodeListAdmin(List<PodcastSeason> podcastSeasons, CreatorClient creatorClient) {
        List<PodcastSeasonDTO> podcastSeasonDTOS = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOS.add(toDTONoEpisodeAdmin(podcastSeason, creatorClient));
        }
        return podcastSeasonDTOS;
    }

    //ED-60-SA
    public static PodcastSeasonDTO toDTONoEpisodeAdmin(PodcastSeason podcastSeason, CreatorClient creatorClient) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setId(podcastSeason.getId());
        podcastSeasonDTO.setActive(podcastSeason.isActive());

        podcastSeasonDTO.setCreators(CreatorMapper.getCreatorsSeasonAdmin(podcastSeason, creatorClient));

        return podcastSeasonDTO;
    }

    //ED-60-SA
    public static List<PodcastSeasonDTO> toDTONoEpisodeListUser(List<PodcastSeason> podcastSeasons, CreatorClient creatorClient) {
        List<PodcastSeasonDTO> podcastSeasonDTOS = new ArrayList<>();
        for (PodcastSeason podcastSeason : podcastSeasons) {
            podcastSeasonDTOS.add(toDTONoEpisodeUser(podcastSeason, creatorClient));
        }
        return podcastSeasonDTOS;
    }

    public static PodcastSeasonDTO toDTONoEpisodeUser(PodcastSeason podcastSeason, CreatorClient creatorClient) {
        PodcastSeasonDTO podcastSeasonDTO = toDTO(podcastSeason);
        podcastSeasonDTO.setCreators(CreatorMapper.getCreatorsSeasonUser(podcastSeason, creatorClient));

        return podcastSeasonDTO;
    }
}
