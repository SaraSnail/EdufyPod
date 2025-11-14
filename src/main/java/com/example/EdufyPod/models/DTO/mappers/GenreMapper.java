package com.example.EdufyPod.models.DTO.mappers;

import com.example.EdufyPod.clients.GenreClient;
import com.example.EdufyPod.models.DTO.GenreDTO;
import com.example.EdufyPod.models.entities.Podcast;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//ED-76-SA
public class GenreMapper {

    public static List<GenreDTO> getGenresAdmin(Podcast podcast, GenreClient genreClient) {
        List<GenreDTO> genres = genreClient.getGenreEpisode(podcast.getId());
        if(genres == null || genres.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"There are no genres in the podcast");
        }

        return genres;
    }

    public static List<GenreDTO> getGenresUser(Podcast podcast, GenreClient genreClient) {

        return getGenresAdmin(podcast, genreClient).stream()
                .map(genre -> {
                    GenreDTO genreDTO = new GenreDTO();
                    genreDTO.setName(genre.getName());
                    return genreDTO;
                })
                .toList();
    }

}
