package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.DTO.CreatorDTO;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;

import java.util.ArrayList;
import java.util.List;

//ED-76-SA
public class CreatorMapper {

    public static List<String> getCreators(Podcast podcast) {
        List<String> creatorUsernames = new ArrayList<>();

        if(podcast.getCreatorsIds() != null) {
            for (Long creatorId : podcast.getCreatorsIds()) {
                //TODO: Call to Creator microservice
                String username = null;
                creatorUsernames.add(username != null ? username : "Creator: " + creatorId);
            }
        }

        return creatorUsernames;
    }

    public static List<String> getCreators(PodcastSeason podcastSeason) {
        List<String> creatorUsernames = new ArrayList<>();

        if(podcastSeason.getCreatorsIds() != null) {
            for (Long creatorId : podcastSeason.getCreatorsIds()) {
                //TODO: Call to Creator microservice
                String username = null;
                creatorUsernames.add(username != null ? username : "Creator: " + creatorId);
            }
        }

        return creatorUsernames;
    }
}
