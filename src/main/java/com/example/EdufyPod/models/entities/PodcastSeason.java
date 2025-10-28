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

    @Column(name = "podcast_season_name", nullable = false, length = 50)
    private String title;

    @Column(name = "podcast_season_description", nullable = false, length = 500)
    private String description;

    @Column(name = "podcast_season_url", nullable = false)
    private String url;

    @Column(name = "podcast_season_release_date", nullable = false)
    private LocalDate releaseDate;

    @OneToMany(mappedBy = "season")
    @JoinColumn(name = "podcast_season_episodes")
    private List<Podcast> podcasts = new ArrayList<>();

    @Column(name = "podcast_season_episode_count")
    private Integer episodeCount;

    /*
    @OneToMany(mappedBy = "")
    @JoinColumn(name = "podcast_season_creators")
    private List<Creator> creators = new ArrayList<>();
    */
    /*
    @OneToMany(mappedBy = "")
    @JoinColumn(name = "podcast_season_genres")
    private List<Genre> genres = new ArrayList<>();
    */

    public PodcastSeason() {
    }

    /*
    //clone constructor
    public PodcastSeason(PodcastSeason podcastSeason) {
        this.title = podcastSeason.getTitle();
        this.description = podcastSeason.getDescription();
        this.url = podcastSeason.getUrl();
        this.releaseDate = podcastSeason.getReleaseDate();
        this.episodeCount = podcastSeason.getEpisodeCount();
        this.creators = podcastSeason.getCreators();
        this.genres = podcastSeason.getGenres();
    }

    public PodcastSeason(Long id, String title, String description, String url, LocalDate releaseDate, List<Podcast> podcasts, Integer episodeCount, List<Creator> creators, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.releaseDate = releaseDate;
        this.podcasts = podcasts;
        this.episodeCount = episodeCount;
        this.creators = creators;
        this.genres = genres;
    }

    public PodcastSeason(String title, String description, String url, LocalDate releaseDate, List<Podcast> podcasts, Integer episodeCount, List<Creator> creators, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.releaseDate = releaseDate;
        this.podcasts = podcasts;
        this.episodeCount = episodeCount;
        this.creators = creators;
        this.genres = genres;
    }

    public PodcastSeason(String title, String description, String url, LocalDate releaseDate, List<Podcast> podcasts, Integer episodeCount) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.releaseDate = releaseDate;
        this.podcasts = podcasts;
        this.episodeCount = episodeCount;
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

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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
                ", creators=" + creators +
                ", genres=" + genres +
                '}';
    }

     */
}
