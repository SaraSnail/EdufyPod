package com.example.EdufyPod.repositories;

import com.example.EdufyPod.models.entities.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//ED-76-SA
@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Long> {
}
