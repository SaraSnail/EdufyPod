package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.DTO.callDTOs.GenreDTO;
import com.example.EdufyPod.models.enums.MediaType;

import java.util.List;

//ED-267-SA
public interface GenreClient {
    List<GenreDTO> getGenreEpisode(Long mediaId);//ED-267-SA
    void createRecordOfMedia(Long mediaId, MediaType mediaType, List<Long> genreIds);

    GenreDTO getGenreById(Long genreId);
}
