package com.example.EdufyPod.services;

import com.example.EdufyPod.models.DTO.CreatorDTO;
import com.example.EdufyPod.services.ClientCalls.CreatorCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreatorServiceImpl implements CreatorService {

    private final CreatorCall creatorCall;

    @Autowired
    public CreatorServiceImpl(CreatorCall creatorCall) {
        this.creatorCall = creatorCall;
    }

    @Override
    public List<CreatorDTO> getCreatorsForEpisode(Long podcastId) {
        return creatorCall.getCreatorsEpisode(podcastId);
    }

    @Override
    public List<CreatorDTO> getCreatorsForSeason(Long seasonId) {
        return creatorCall.getCreatorsSeason(seasonId);
    }
}
