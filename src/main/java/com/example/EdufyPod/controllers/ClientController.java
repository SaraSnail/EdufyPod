package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.PodcastResponse;
import com.example.EdufyPod.services.PodcastAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//ED-60-SA
@RestController
@RequestMapping("/api/v1/pod")
public class ClientController {

    private final PodcastAggregationService podcastAggregationService;

    //ED-60-SA
    @Autowired
    public ClientController(PodcastAggregationService podcastAggregationService) {
        this.podcastAggregationService = podcastAggregationService;
    }

    //ED-60-SA
    @GetMapping("/pod-creator")
    public ResponseEntity<PodcastResponse> getPodByCreator(@RequestParam List<Long> seasonIds, @RequestParam List<Long> podcastIds) {
        PodcastResponse response = podcastAggregationService.getPodcastsAndSeasonsByIds(seasonIds, podcastIds);

        //Checks if there were any missing ids on podcast episodes or season, if so the status is changed to Partial content
        //(it won't throw, just inform that it could not find everything)
        boolean hasMissingIds = !response.missingEpisodeIds().isEmpty() || !response.missingSeasonIds().isEmpty();
        HttpStatus status = hasMissingIds ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK;

        return ResponseEntity.status(status).body(response);
    }
}
