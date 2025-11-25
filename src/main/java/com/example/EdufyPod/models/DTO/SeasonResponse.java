package com.example.EdufyPod.models.DTO;

import java.util.List;

//ED-231-SA
//ED-348-SA - removed missing Ids
public record SeasonResponse(
        List<PodcastSeasonDTO> seasons
) { }
