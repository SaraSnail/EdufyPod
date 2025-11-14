package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.DTO.GenreDTO;
import java.util.List;

//ED-267-SA
public interface GenreClient {
    List<GenreDTO> getGenreEpisode(Long mediaId);//ED-267-SA
}
