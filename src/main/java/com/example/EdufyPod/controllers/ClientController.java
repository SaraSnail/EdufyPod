package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.*;
import com.example.EdufyPod.services.PodcastAggregationServiceImpl;
import com.example.EdufyPod.services.PodcastService;
import com.example.EdufyPod.services.PodcastServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//ED-60-SA
@RestController
@RequestMapping("/pod")
public class ClientController {

    private final PodcastAggregationServiceImpl podcastAggregationService;
    private final PodcastService podcastService;

    //ED-60-SA
    @Autowired
    public ClientController(PodcastAggregationServiceImpl podcastAggregationService, PodcastService podcastService) {
        this.podcastAggregationService = podcastAggregationService;
        this.podcastService = podcastService;
    }

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

    //ED-283-SA
    @GetMapping("/user-history/{userId}")
    public ResponseEntity<List<PodcastDTO>> getUserHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(podcastService.getUserHistory(userId));
    }
}
