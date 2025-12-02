package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastResponse;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.services.PodcastAggregationService;
import com.example.EdufyPod.services.PodcastSeasonService;
import com.example.EdufyPod.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//ED-82-SA
@RestController
@RequestMapping("/pod")
@PreAuthorize("hasAnyRole('pod_admin', 'edufy_realm_admin', 'pod_user')")
public class CommonController {

    private final PodcastService podcastService;
    private final PodcastSeasonService podcastSeasonService;
    private final PodcastAggregationService podcastAggregationService;

    @Autowired
    public CommonController(PodcastService podcastService, PodcastSeasonService podcastSeasonService, PodcastAggregationService podcastAggregationService) {
        this.podcastService = podcastService;
        this.podcastSeasonService = podcastSeasonService;
        this.podcastAggregationService = podcastAggregationService;
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

    //ED-348-SA - moved from ClientController
    //ED-60-SA
    //ED-303
    //ED-348-SA - removed missing Ids
    @GetMapping("/pod-creator/{creatorId}")//ED-303-SA - GET mapping to POST mapping
    public ResponseEntity<PodcastResponse> getPodByCreator(@PathVariable Long creatorId, Authentication auth) {
        return ResponseEntity.ok(podcastAggregationService.getPodcastsAndSeasonsByIds(creatorId, auth));
    }

    //ED-348-SA - moved from ClientController
    //ED-231-SA: gets seasons, they also contains the seasons episodes
    //ED-348-SA - removed missing Ids
    @GetMapping("/season-creator/{creatorId}")//ED-303-SA - GET mapping to POST mapping
    public ResponseEntity<List<PodcastSeasonDTO>> getSeasonByCreator(@PathVariable Long creatorId, Authentication authentication) {
        return ResponseEntity.ok(podcastAggregationService.getSeasonsByIds(creatorId, authentication));
    }
}
