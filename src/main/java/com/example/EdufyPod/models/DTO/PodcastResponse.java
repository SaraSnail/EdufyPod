package com.example.EdufyPod.models.DTO;

import java.util.List;

//ED-60-SA
public record PodcastResponse(
        List<PodcastSeasonDTO> seasons,
        List<PodcastDTO> episodes,
        List<Long> missingEpisodeIds,
        List<Long> missingSeasonIds
) { }
