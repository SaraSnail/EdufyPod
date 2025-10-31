package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.services.PodcastSeasonService;
import com.example.EdufyPod.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pod")
public class AdminController {

    private final PodcastService podcastService;
    private final PodcastSeasonService podcastSeasonService;

    @Autowired
    public AdminController(PodcastService podcastService, PodcastSeasonService podcastSeasonService) {
        this.podcastService = podcastService;
        this.podcastSeasonService = podcastSeasonService;
    }

    @GetMapping("/getpodcastbyid/{id}")
    public ResponseEntity<PodcastDTO> getPodcastById(@PathVariable Long id) {
        return ResponseEntity.ok(podcastService.getPodcastById(id));
    }

    @GetMapping("/getpodcastseasonbyid/{id}")
    public ResponseEntity<PodcastSeasonDTO> getPodcastSeasonById(@PathVariable Long id) {
        return ResponseEntity.ok(podcastSeasonService.getPodcastSeasonById(id));
    }
}
