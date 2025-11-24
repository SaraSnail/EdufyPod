package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.PodcastResponse;
import com.example.EdufyPod.models.DTO.PodcastSeasonDTO;
import com.example.EdufyPod.models.DTO.SeasonResponse;
import com.example.EdufyPod.services.PodcastAggregationService;
import com.example.EdufyPod.services.PodcastSeasonService;
import com.example.EdufyPod.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//ED-82-SA
@RestController
@RequestMapping("/pod")
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
    @GetMapping("/pod-creator/{creatorId}")//ED-303-SA - GET mapping to POST mapping
    public ResponseEntity<PodcastResponse> getPodByCreator(@PathVariable Long creatorId, Authentication auth) {
        PodcastResponse response = podcastAggregationService.getPodcastsAndSeasonsByIds(creatorId, auth);

        //Checks if there were any missing ids on podcast episodes or season, if so the status is changed to Partial content
        //(it won't throw, just inform that it could not find everything)
        boolean hasMissingIds = !response.missingEpisodeIds().isEmpty() || !response.missingSeasonIds().isEmpty();
        HttpStatus status = hasMissingIds ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK;

        return ResponseEntity.status(status).body(response);
    }

    //ED-348-SA - moved from ClientController
    //ED-231-SA: gets seasons, they also contains the seasons episodes
    @GetMapping("/season-creator/{creatorId}")//ED-303-SA - GET mapping to POST mapping
    public ResponseEntity<SeasonResponse> getSeasonByCreator(@PathVariable Long creatorId, Authentication authentication) {//makes so if withId is not given just set value to false
        SeasonResponse response = podcastAggregationService.getSeasonsByIds(creatorId, authentication);

        //Checks if there were any missing ids on podcast season, if so the status is changed to Partial content
        //(it won't throw, just inform that it could not find everything)
        boolean hasMissingIds = !response.missingSeasonIds().isEmpty();
        HttpStatus status = hasMissingIds ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK;

        return ResponseEntity.status(status).body(response);
    }
}
