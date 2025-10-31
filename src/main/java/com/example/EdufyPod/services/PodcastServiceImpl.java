package com.example.EdufyPod.services;

import com.example.EdufyPod.exceptions.ResourceNotFoundException;
import com.example.EdufyPod.models.DTO.PodcastDTO;
import com.example.EdufyPod.models.DTO.mappers.PodcastMapper;
import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.repositories.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//ED-76-SA
@Service
public class PodcastServiceImpl implements PodcastService {

    private final PodcastRepository podcastRepository;

    //ED-76-SA
    @Autowired
    public PodcastServiceImpl(PodcastRepository podcastRepository) {
        this.podcastRepository = podcastRepository;
    }

    //ED-76-SA
    @Override
    public PodcastDTO getPodcastById(Long id) {
        Optional<Podcast> findPodcast = podcastRepository.findById(id);
        if(findPodcast.isEmpty()){
            throw new ResourceNotFoundException("Podcast", "id", id);
        }
        Podcast podcast = findPodcast.get();
        return PodcastMapper.toDTOWithId(podcast);
    }

    //ED-56-SA
    @Override
    public List<PodcastDTO> getPodcastByTitle(String title) {
        List<Podcast> podcasts = podcastRepository.findAllByTitleContainingIgnoreCase(title);
        if(podcasts.isEmpty()){
            throw new ResourceNotFoundException("Podcast", "title containing", title);
        }
        return PodcastMapper.toDTOWithIdList(podcasts);
    }
}
