package com.example.EdufyPod.services;

import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.mappers.PodcastMapper;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.repositories.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class PodcastServiceImpl implements PodcastService {

    private final PodcastRepository podcastRepository;

    @Autowired
    public PodcastServiceImpl(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    @Override
    public PodcastDTO getPodcastById(Long id) {
        Optional<Podcast> findPodcast = podcastRepository.findById(id);
        if(findPodcast.isEmpty()){
            throw new ResourceNotFoundException("Podcast", "id", id);
        }
        Podcast podcast = findPodcast.get();
        return PodcastMapper.toDTO(podcast);
    }
}
