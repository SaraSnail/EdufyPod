package com.example.EdufyPod.repositories;

import com.example.EdufyPod.models.entities.Podcast;
import com.example.EdufyPod.models.entities.PodcastSeason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodcastSeasonRepository extends JpaRepository<PodcastSeason, Long> {
}
