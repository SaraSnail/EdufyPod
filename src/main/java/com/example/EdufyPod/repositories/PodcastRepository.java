package com.example.EdufyPod.repositories;

import com.example.EdufyPod.models.entities.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

//ED-76-SA
@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Long> {

    List<Podcast> findAllByTitleContainingIgnoreCaseAndIsActiveTrue(String title);//ED-56-SA + ED-219-SA (isActive=true)
    List<Podcast> findAllByIsActiveTrue();//ED-82-SA
    List<Podcast> findAllByIdInAndIsActiveTrue(List<Long> ids);//ED-60-SA
    Boolean existsByUrl(String url);//ED-232-SA

    //ED-232-SA
    @Query("SELECT p FROM Podcast p WHERE p.season.id = :seasonId ORDER BY p.nrInSeason ASC")
    List<Podcast> findAllBySeasonOrdered(@Param("seasonId") Long seasonId);


    //ED-283-SA
    @Query(value = """
        SELECT p.podcast_episode_id
        FROM podcast_episode_user_history uh
        JOIN podcast_episode p ON p.podcast_episode_id = uh.podcast_episode_id
        WHERE uh.user_id = :userId
        """, nativeQuery = true)
    List<Long> findPodcastIdsPlayedByUser(@Param("userId") Long userId);


    //ED-254-SA
    Podcast findByIdAndIsActiveTrue(Long id);
}
