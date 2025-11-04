package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.services.PodcastSeasonService;
import com.example.EdufyPod.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pod")
public class CommonController {

    private final PodcastService podcastService;
    private final PodcastSeasonService podcastSeasonService;

    @Autowired
    public CommonController(PodcastService podcastService, PodcastSeasonService podcastSeasonService) {
        this.podcastService = podcastService;
        this.podcastSeasonService = podcastSeasonService;
    }

    @GetMapping("/episode-all")
    public ResponseEntity<List<PodcastDTO>> getAllPodcasts(Authentication authentication) {
        return ResponseEntity.ok(podcastService.getAllPodcasts(authentication));
    }
}
