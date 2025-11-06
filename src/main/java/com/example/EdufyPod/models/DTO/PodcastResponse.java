package com.example.EdufyPod.models.DTO;

import java.util.List;

public record PodcastResponse(
        List<PodcastDTO> episodes,
        List<PodcastSeasonDTO> seasons
) { }
