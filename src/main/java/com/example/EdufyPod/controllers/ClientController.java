package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.PodcastResponse;
import com.example.EdufyPod.services.PodcastAggregationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pod")
public class ClientController {

    private final PodcastAggregationService podcastAggregationService;

    @Autowired
    public ClientController(PodcastAggregationService podcastAggregationService) {
        this.podcastAggregationService = podcastAggregationService;
    }

    @GetMapping("/pod-creator")
    public ResponseEntity<PodcastResponse> getPodByCreator(@RequestParam List<Long> podcastIds, @RequestParam List<Long> seasonIds) {
        PodcastResponse response = podcastAggregationService.getPodcastsAndSeasonsByIds(podcastIds, seasonIds);
        return ResponseEntity.ok(response);
    }
}
