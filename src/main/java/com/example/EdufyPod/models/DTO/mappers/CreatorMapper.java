package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.GenreDTO;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;
import com.example.EdufyPod.services.ClientCalls.CreatorCall;
import com.example.EdufyPod.services.ClientCalls.CreatorCallImpl;

import java.util.List;

//ED-76-SA
//ED-303-SA
public class CreatorMapper {

    private final CreatorCall creatorCall;

    public CreatorMapper(CreatorCall creatorCall) {
        this.creatorCall = creatorCall;
    }

    //ED-303-SA
    public static List<GenreDTO.CreatorDTO> getCreatorsPodcastAdmin(Podcast podcast) {
        CreatorCallImpl creatorCallImpl = new CreatorCallImpl();
        List<GenreDTO.CreatorDTO> creators = creatorCallImpl.getCreatorsEpisode(podcast.getId());
        if (creators == null || creators.isEmpty()) {
            throw new RuntimeException("There are no creators in the podcast");
        }

        return creators;
    }

    //ED-303-SA
    public static List<GenreDTO.CreatorDTO> getCreatorsPodcastUser(Podcast podcast) {
        return  getCreatorsPodcastAdmin(podcast).stream()
                .map(creator -> {
                    GenreDTO.CreatorDTO creatorDTO = new GenreDTO.CreatorDTO();
                    creatorDTO.setUsername(creator.getUsername());
                    return creatorDTO;
                })
                .toList();

    }

    //ED-303-SA
    public static List<GenreDTO.CreatorDTO> getCreatorsSeasonAdmin(PodcastSeason season) {
        CreatorCallImpl creatorCallImpl = new CreatorCallImpl();
        List<GenreDTO.CreatorDTO> creators = creatorCallImpl.getCreatorsSeason(season.getId());
        if (creators == null || creators.isEmpty()) {
            throw new RuntimeException("There are no creators in the podcast");
        }

        return creators;
    }

    //ED-303-SA
    public static List<GenreDTO.CreatorDTO> getCreatorsSeasonUser(PodcastSeason season) {
        return  getCreatorsSeasonAdmin(season).stream()
                .map(creator -> {
                    GenreDTO.CreatorDTO creatorDTO = new GenreDTO.CreatorDTO();
                    creatorDTO.setUsername(creator.getUsername());
                    return creatorDTO;
                })
                .toList();
    }
}
