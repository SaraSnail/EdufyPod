package com.example.EdufyPod.models.DTO;

import java.util.List;

//ED-60-SA
//ED-348-SA - removed missing Ids
public record PodcastResponse(
        List<PodcastSeasonDTO> seasons,
        List<PodcastDTO> episodes
) { }
