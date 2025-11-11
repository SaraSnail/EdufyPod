package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.services.PodcastSeasonService;
import com.example.EdufyPod.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//ED-82-SA
@RestController
@RequestMapping("/pod")
public class CommonController {

    private final PodcastService podcastService;
    private final PodcastSeasonService podcastSeasonService;

    @Autowired
    public CommonController(PodcastService podcastService, PodcastSeasonService podcastSeasonService) {
        this.podcastService = podcastService;
        this.podcastSeasonService = podcastSeasonService;
    }

    //ED-82-SA
    @GetMapping("/episode-all")
    public ResponseEntity<List<PodcastDTO>> getAllPodcasts(Authentication authentication) {
        return ResponseEntity.ok(podcastService.getAllPodcasts(authentication));
    }

    //ED-83-SA
    @GetMapping("/season-all")
    public ResponseEntity<List<PodcastSeasonDTO>> getAllPodcastSeasons(Authentication authentication) {
        return ResponseEntity.ok(podcastSeasonService.getAllPodcastSeasons(authentication));
    }
}
