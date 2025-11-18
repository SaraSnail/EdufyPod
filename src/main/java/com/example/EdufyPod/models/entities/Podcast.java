package com.example.EdufyPod.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//ED-40-SA
//ED-112-SA
@Entity
@Table(name = "podcast_episode")
public class Podcast {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "podcast_episode_id")
    private Long id;

    @Column(name = "podcast_episode_title", nullable = false, length = 50)
    private String title;

    @Column(name = "podcast_episode_url", nullable = false, unique = true)
    private String url;

    @Column(name = "podcast_episode_description", nullable = false)
    private String description;

    //ED-112-SA
    @Column(name = "podcast_episode_release_date", nullable = false)
    private LocalDate releaseDate;

    //ED-112-SA
    @Column(name = "podcast_episode_length", nullable = false)
    private Duration length;//ED-232-SA

    @Column(name = "podcast_episode_nr_in_season")
    private int nrInSeason;


    @ManyToOne
    @JoinColumn(name = "podcast_episode_podcast_season")
    private PodcastSeason season;


    @Column(name = "podcast_episode_times_listened")
    private Integer timesListened;

    @JsonProperty("isActive")//ED-76-SA
    @Column(name = "podcast_episode_is_active")
    private boolean isActive;


    //ED-283-SA
    @ElementCollection
    @CollectionTable(
            name = "podcast_episode_user_history",
            joinColumns = @JoinColumn(name = "podcast_episode_id")
    )
    @MapKeyColumn(name = "user_id")//Key column
    @Column(name = "times_played")//Value column
    private Map<Long, Long> userHistory = new HashMap<>();

    public Podcast() {
    }


    public Podcast(Podcast podcast) {
        this.title = podcast.getTitle();
        this.url = podcast.getUrl();
        this.description = podcast.getDescription();
        this.releaseDate = podcast.getReleaseDate();
        this.length = podcast.getLength();
        this.nrInSeason = podcast.getNrInSeason();
        this.season = podcast.getSeason();
        this.timesListened = podcast.getTimesListened();
        this.isActive = podcast.isActive();
        this.userHistory = podcast.getUserHistory();
    }

    public Podcast(Long id, String title, String url, String description, LocalDate releaseDate, Duration length, int nrInSeason, PodcastSeason season, Integer timesListened, boolean isActive, Map<Long, Long> userHistory) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.releaseDate = releaseDate;
        this.length = length;
        this.nrInSeason = nrInSeason;
        this.season = season;
        this.timesListened = timesListened;
        this.isActive = isActive;
        this.userHistory = userHistory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Duration getLength() {
        return length;
    }

    public void setLength(Duration length) {
        this.length = length;
    }

    public int getNrInSeason() {
        return nrInSeason;
    }

    public void setNrInSeason(int nrInSeason) {
        this.nrInSeason = nrInSeason;
    }

    public PodcastSeason getSeason() {
        return season;
    }

    public void setSeason(PodcastSeason season) {
        this.season = season;
    }

    public Integer getTimesListened() {
        return timesListened;
    }

    public void setTimesListened(Integer timesListened) {
        this.timesListened = timesListened;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Map<Long, Long> getUserHistory() {
        return userHistory;
    }

    public void setUserHistory(Map<Long, Long> userHistory) {
        this.userHistory = userHistory;
    }

    //ED-283-SA
    public void incrementTimesPlayed(Long userId) {
        userHistory.merge(userId, 1L, Long::sum);
    }

    //ED-283-SA
    public Long getTimesPlayed(Long userId) {
        return userHistory.getOrDefault(userId, 0L);
    }

    //ED-283-SA
    public void setTimesPlayed(Long userId, Long timesPlayed) {
        userHistory.put(userId, timesPlayed);
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", releaseDate=" + releaseDate +
                ", length=" + length +
                ", nrInSeason=" + nrInSeason +
                ", season=" + season +
                ", timesListened=" + timesListened +
                ", isActive=" + isActive +
                ", userHistory=" + userHistory +
                '}';
    }
}
