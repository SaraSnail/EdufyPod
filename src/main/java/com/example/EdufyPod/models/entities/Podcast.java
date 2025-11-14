package com.example.EdufyPod.models.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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

    //ED-118-SA
    //ED-119-SA: had connected it wrong
    @ElementCollection
    @CollectionTable(
            name = "podcast_episode_creators",
            joinColumns = @JoinColumn(name = "podcast_episode_id")
    )
    @Column(name = "creator_id", nullable = false)
    private List<Long> creatorsIds = new ArrayList<>();

    //ED-112-SA
    @Column(name = "podcast_episode_release_date", nullable = false)
    private LocalDate releaseDate;

    //ED-112-SA
    @Column(name = "podcast_episode_length", nullable = false)
    private LocalTime length;

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

    public Podcast() {
    }


    public Podcast(Podcast podcast) {
        this.title = podcast.getTitle();
        this.url = podcast.getUrl();
        this.description = podcast.getDescription();
        this.creatorsIds = podcast.getCreatorsIds();
        this.releaseDate = podcast.getReleaseDate();
        this.length = podcast.getLength();
        this.nrInSeason = podcast.getNrInSeason();
        this.season = podcast.getSeason();
        this.timesListened = podcast.getTimesListened();
        this.isActive = podcast.isActive();
    }

    public Podcast(Long id, String title, String url, String description, List<Long> creatorsIds, LocalDate releaseDate, LocalTime length, int nrInSeason, PodcastSeason season, Integer timesListened, boolean isActive) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.creatorsIds = creatorsIds;
        this.releaseDate = releaseDate;
        this.length = length;
        this.nrInSeason = nrInSeason;
        this.season = season;
        this.timesListened = timesListened;
        this.isActive = isActive;
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

    public List<Long> getCreatorsIds() {
        return creatorsIds;
    }

    public void setCreatorsIds(List<Long> creatorsIds) {
        this.creatorsIds = creatorsIds;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalTime getLength() {
        return length;
    }

    public void setLength(LocalTime length) {
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

    @Override
    public String toString() {
        return "Podcast{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", creatorsIds=" + creatorsIds +
                ", releaseDate=" + releaseDate +
                ", length=" + length +
                ", nrInSeason=" + nrInSeason +
                ", season=" + season +
                ", timesListened=" + timesListened +
                ", isActive=" + isActive +
                '}';
    }
}
