package com.example.EdufyPod.models.DTO;

import java.util.List;

//ED-231-SA
public record SeasonResponse(
        List<Long> missingSeasonIds,
        List<PodcastSeasonDTO> seasons
) { }
