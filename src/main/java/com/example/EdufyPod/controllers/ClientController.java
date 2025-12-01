package com.example.EdufyPod.controllers;

import com.example.EdufyPod.models.DTO.*;
import com.example.EdufyPod.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//ED-60-SA
@RestController
@RequestMapping("/pod")
@PreAuthorize("hasRole('microservice_access')")//ED-348-SA
public class ClientController {
    private final PodcastService podcastService;

    //ED-60-SA
    @Autowired
    public ClientController(PodcastService podcastService) {
        this.podcastService = podcastService;
    }

    //ED-283-SA
    @GetMapping("/user-history/{userId}")
    public ResponseEntity<List<PodcastDTO>> getUserHistory(@PathVariable Long userId) {
        return ResponseEntity.ok(podcastService.getUserHistory(userId));
    }
}
