package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.repositories.PodcastRepository;
import com.example.EdufyPod.repositories.PodcastSeasonRepository;
import com.example.EdufyPod.services.PodcastSeasonService;
import com.example.EdufyPod.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//ED-76-SA
//ED-56-SA
@RestController
@RequestMapping("/api/v1/pod")
public class UserController {

    private final PodcastService podcastService;
    private final PodcastSeasonService podcastSeasonService;

    //ED-56-SA
    public UserController(PodcastService podcastService, PodcastSeasonService podcastSeasonService) {
        this.podcastService = podcastService;
        this.podcastSeasonService = podcastSeasonService;
    }

    //ED-56-SA
    @GetMapping("/getpodcastbytitle")
    public ResponseEntity<List<PodcastDTO>> getPodcastByTitle(@RequestParam String title) {
        return ResponseEntity.ok(podcastService.getPodcastByTitle(title));
    }

    //ED-58-SA
    @GetMapping("/getpodcastseasonbytitle")
    public ResponseEntity<List<PodcastSeasonDTO>> getPodcastSeasonByTitle(@RequestParam String title) {
        return ResponseEntity.ok(podcastSeasonService.getPodcastSeasonByTitle(title));
    }
}
