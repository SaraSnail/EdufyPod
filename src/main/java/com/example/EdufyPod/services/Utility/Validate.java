package com.example.EdufyPod.services.Utility;

import com.example.EdufyPod.clients.CreatorClient;
import com.example.EdufyPod.clients.GenreClient;
import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.exceptions.UniqueConflictException;
import com.example.EdufyPod.models.DTO.callDTOs.CreatorDTO;
import com.example.EdufyPod.models.DTO.callDTOs.GenreDTO;
import org.springframework.web.client.RestClientResponseException;

import java.util.List;

//ED-242-SA
public class Validate {

    //ED-232-SA
    public static void validateCreators(List<Long> creatorIds, CreatorClient creatorClient) {
        for(Long creatorId : creatorIds){
            try{
                CreatorDTO creator = creatorClient.getCreatorById(creatorId);
                Long id = creator.getId();
            }catch (RestClientResponseException e){
                throw new ResourceNotFoundException("Creator", "id", creatorId);
            }
        }

    }

    //ED-232-SA
    public static void validateGenres(List<Long> genreIds, GenreClient genreClient) {

        for(Long genreId : genreIds){
            try{
                GenreDTO genreDTO = genreClient.getGenreById(genreId);
                Long id = genreDTO.getGenre_id();
            }catch (RestClientResponseException e){
                throw new ResourceNotFoundException("Genre", "id", genreId);
            }
        }

    }
}
