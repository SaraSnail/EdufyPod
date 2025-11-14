package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;

import java.util.ArrayList;
import java.util.List;

//ED-76-SA
public class GenreMapping {

    public static List<String> getGenres(Podcast podcast) {
        List<String> genres = new ArrayList<>();

        /*
        if(podcast.getCreatorsIds() != null) {
            for (Long genreId : podcast.getGenresIds()) {
                //TODO: Call to Genre microservice
                String genre = null;
                genres.add(genre != null ? genre : "Genre: " + genreId);
            }
        }*/

        return genres;
    }

    public static List<String> getGenres(PodcastSeason podcastSeason) {
        List<String> genres = new ArrayList<>();

        if(podcastSeason.getGenresIds() != null) {
            for (Long genreId : podcastSeason.getGenresIds()) {
                //TODO: Call to Genre microservice
                String genre = null;
                genres.add(genre != null ? genre : "Genre: " + genreId);
            }
        }

        return genres;
    }
}
