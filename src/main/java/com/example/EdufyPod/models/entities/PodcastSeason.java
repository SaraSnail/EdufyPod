package com.example.EdufyPod.models.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//ED-40-SA
//ED-117-SA
@Entity
@Table(name = "podcast_season")
public class PodcastSeason {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "podcast_season_id")
    private Long id;

    @Column(name = "podcast_season_title", nullable = false, length = 50)
    private String title;

    @Column(name = "podcast_season_description", nullable = false, length = 500)
    private String description;

    @Column(name = "podcast_season_url", nullable = false, unique = true)
    private String url;

    @Column(name = "podcast_season_release_date", nullable = false)
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "season")
    @JoinColumn(name = "podcast_season_episodes")
    private List<Podcast> podcasts = new ArrayList<>();

    @Column(name = "podcast_season_episode_count")
    private Integer episodeCount;

    //ED-118-SA
    //ED-119-SA: had connected it wrong
    @ElementCollection
    @CollectionTable(
            name = "podcast_season_creator",
            joinColumns = @JoinColumn(name = "podcast_season_id")
    )
    @Column(name = "creator_id", nullable = false)
    private List<Long> creatorsIds = new ArrayList<>();

    //ED-119-SA
    @ElementCollection
    @CollectionTable(
            name = "podcast_season_genre",
            joinColumns = @JoinColumn(name = "podcast_season_id")
    )
    @Column(name = "genre_id", nullable = false)
    private List<Long> genresIds = new ArrayList<>();

    //ED-117-SA
    @Column(name = "podcast_season_is_active")
    private boolean isActive;

    public PodcastSeason() {
    }


    //clone constructor
    public PodcastSeason(PodcastSeason podcastSeason) {
        this.title = podcastSeason.getTitle();
        this.description = podcastSeason.getDescription();
        this.url = podcastSeason.getUrl();
        this.releaseDate = podcastSeason.getReleaseDate();
        this.episodeCount = podcastSeason.getEpisodeCount();
        this.creatorsIds = podcastSeason.getCreatorsIds();
        this.genresIds = podcastSeason.getGenresIds();
        this.isActive = podcastSeason.isActive();
    }

    public PodcastSeason(Long id, String title, String description, String url, LocalDate releaseDate, List<Podcast> podcasts, Integer episodeCount, List<Long> creatorsIds, List<Long> genresIds, boolean isActive) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.releaseDate = releaseDate;
        this.podcasts = podcasts;
        this.episodeCount = episodeCount;
        this.creatorsIds = creatorsIds;
        this.genresIds = genresIds;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    public List<Long> getCreatorsIds() {
        return creatorsIds;
    }

    public void setCreatorsIds(List<Long> creatorsIds) {
        this.creatorsIds = creatorsIds;
    }

    public List<Long> getGenresIds() {
        return genresIds;
    }

    public void setGenresIds(List<Long> genresIds) {
        this.genresIds = genresIds;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "PodcastSeason{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", releaseDate=" + releaseDate +
                ", podcasts=" + podcasts +
                ", episodeCount=" + episodeCount +
                ", creatorsIds=" + creatorsIds +
                ", genresIds=" + genresIds +
                ", isActive=" + isActive +
                '}';
    }
}
