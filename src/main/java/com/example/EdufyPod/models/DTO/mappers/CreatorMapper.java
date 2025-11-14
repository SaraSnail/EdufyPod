package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.CreatorDTO;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;
import com.example.EdufyPod.services.ClientCalls.CreatorCall;
import org.springframework.stereotype.Component;

import java.util.List;

//ED-76-SA
//ED-303-SA
@Component//ED-276-SA
public class CreatorMapper {

    private final CreatorCall creatorCall;//ED-276-SA

    //ED-276-SA
    public CreatorMapper(CreatorCall creatorCall) {
        this.creatorCall = creatorCall;
    }

    //ED-276-SA
    public static CreatorDTO toDTOUser(CreatorDTO creator){
        CreatorDTO dto = new CreatorDTO();
        dto.setUsername(creator.getUsername());
        return dto;
    }

    //ED-276-SA
    public static List<CreatorDTO> toDTOUserList(List<CreatorDTO> creators){
        return creators.stream()
                .map(CreatorMapper::toDTOUser)
                .toList();
    }

    //ED-276-SA
    public static CreatorDTO toDTOAdmin(CreatorDTO creator){
        CreatorDTO dto = new CreatorDTO();
        dto.setId(creator.getId());
        dto.setUsername(creator.getUsername());
        return dto;
    }

    //ED-276-SA
    public static List<CreatorDTO> toDTOAdminList(List<CreatorDTO> creators){
        return creators.stream()
                .map(CreatorMapper::toDTOAdmin)
                .toList();
    }

    //ED-303-SA
    public List<CreatorDTO> getCreatorsPodcastAdmin(Podcast podcast) {
        List<CreatorDTO> creators = creatorCall.getCreatorsEpisode(podcast.getId());
        if (creators == null || creators.isEmpty()) {
            throw new RuntimeException("There are no creators in the podcast");
        }

        return creators;
    }





    //ED-303-SA
    public List<CreatorDTO> getCreatorsPodcastUser(Podcast podcast) {
        return  getCreatorsPodcastAdmin(podcast).stream()
                .map(creator -> {
                    CreatorDTO creatorDTO = new CreatorDTO();
                    creatorDTO.setUsername(creator.getUsername());
                    return creatorDTO;
                })
                .toList();

    }

    //ED-303-SA
    public List<CreatorDTO> getCreatorsSeasonAdmin(PodcastSeason season) {
        List<CreatorDTO> creators = creatorCall.getCreatorsSeason(season.getId());
        if (creators == null || creators.isEmpty()) {
            throw new RuntimeException("There are no creators in the podcast");
        }

        return creators;
    }

    //ED-303-SA
    public List<CreatorDTO> getCreatorsSeasonUser(PodcastSeason season) {
        return  getCreatorsSeasonAdmin(season).stream()
                .map(creator -> {
                    CreatorDTO creatorDTO = new CreatorDTO();
                    creatorDTO.setUsername(creator.getUsername());
                    return creatorDTO;
                })
                .toList();
    }
}
