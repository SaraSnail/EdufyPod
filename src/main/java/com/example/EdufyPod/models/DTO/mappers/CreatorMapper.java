package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.callDTOs.CreatorDTO;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;
import com.example.EdufyPod.clients.CreatorClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//ED-76-SA
//ED-303-SA
public class CreatorMapper {

    //ED-303-SA
    public static List<CreatorDTO> getCreatorsPodcastAdmin(Podcast podcast, CreatorClient creatorClient) {
        List<CreatorDTO> creators = creatorClient.getCreatorsEpisode(podcast.getId());
        if (creators == null || creators.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"There are no creators in the podcast");
        }

        return creators;
    }

    //ED-303-SA
    public static List<CreatorDTO> getCreatorsPodcastUser(Podcast podcast, CreatorClient creatorClient) {
        return  getCreatorsPodcastAdmin(podcast, creatorClient).stream()
                .map(creator -> {
                    CreatorDTO creatorDTO = new CreatorDTO();
                    creatorDTO.setUsername(creator.getUsername());
                    return creatorDTO;
                })
                .toList();

    }

    //ED-303-SA
    public static List<CreatorDTO> getCreatorsSeasonAdmin(PodcastSeason season, CreatorClient creatorClient) {
        List<CreatorDTO> creators = creatorClient.getCreatorsSeason(season.getId());
        if (creators == null || creators.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"There are no creators in the podcast");
        }

        return creators;
    }

    //ED-303-SA
    public static List<CreatorDTO> getCreatorsSeasonUser(PodcastSeason season, CreatorClient creatorClient) {
        return  getCreatorsSeasonAdmin(season, creatorClient).stream()
                .map(creator -> {
                    CreatorDTO creatorDTO = new CreatorDTO();
                    creatorDTO.setUsername(creator.getUsername());
                    return creatorDTO;
                })
                .toList();
    }
}
