package com.example.EdufyPod.services;

import com.example.EdufyPod.models.DTO.PodcastDTO;

import java.util.List;

//ED-76-SA
public interface PodcastService {

    PodcastDTO getPodcastById(Long id);//ED-76-SA
    List<PodcastDTO> getPodcastByTitle(String title);
}
