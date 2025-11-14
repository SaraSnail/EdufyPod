package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.callDTOs.IncomingPodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.services.PodcastSeasonService;
import com.example.EdufyPod.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

//ED-76-SA
@RestController
@RequestMapping("/pod")
@PreAuthorize("hasAnyAuthority('pod_admin','edufy_realm_admin')")
public class AdminController {

    private final PodcastService podcastService;
    private final PodcastSeasonService podcastSeasonService;

    //ED-76-SA
    @Autowired
    public AdminController(PodcastService podcastService, PodcastSeasonService podcastSeasonService) {
        this.podcastService = podcastService;
        this.podcastSeasonService = podcastSeasonService;
    }

    //ED-76-SA
    @GetMapping("/episode-id/{id}")//ED-249-SA: renamed endpoint
    public ResponseEntity<PodcastDTO> getPodcastById(@PathVariable Long id) {
        return ResponseEntity.ok(podcastService.getPodcastById(id));
    }

    //ED-77-SA
    @GetMapping("/season-id/{id}")//ED-249-SA: renamed endpoint
    public ResponseEntity<PodcastSeasonDTO> getPodcastSeasonById(@PathVariable Long id) {
        return ResponseEntity.ok(podcastSeasonService.getPodcastSeasonById(id));
    }

    //ED-232-SA
    @PostMapping("/new-episode")
    public ResponseEntity<PodcastDTO> createPodcast(@RequestBody IncomingPodcastDTO incomingPodcastDTO) {
        return new ResponseEntity<>(podcastService.createPodcast(incomingPodcastDTO), HttpStatus.CREATED);
    }

}
