package com.example.EdufyPod.models.entities;

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

    @Column(name = "podcast_episode_url", nullable = false)
    private String url;

    @Column(name = "podcast_episode_description", nullable = false)
    private String description;

    /*
    @ManyToMany
    @JoinColumn(name = "podcast_episode_creators", nullable = false)
    private List<Creator> creators = new ArrayList<>();
    */

    @Column(name = "podcast_episode_release_date", nullable = false)
    private LocalDate releaseDate;

    /*
    @ManyToMany
    @JoinColumn(name = "podcast_episode_genres", nullable = false)
    private List<Genre> genres;
    */

    @Column(name = "podcast_episode_length", nullable = false)
    private LocalTime length;

    @Column(name = "podcast_episode_nr_in_season")
    private int nrInSeason;

    /*
    @ManyToOne
    @JoinColumn(name = "podcast_episode_podcast_season")
    private PodcastSeason season;
    */

    @Column(name = "podcast_episode_is_active")
    private boolean isActive;

    public Podcast() {
    }
/*
    public Podcast(String title, String url, String description, LocalDate releaseDate, LocalTime length, int nrInSeason, PodcastSeason season, boolean isActive) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.releaseDate = releaseDate;
        this.length = length;
        this.nrInSeason = nrInSeason;
        this.season = season;
        this.isActive = isActive;
    }

    public Podcast(String title, String url, String description, List<Creator> creators, LocalDate releaseDate, List<Genre> genres, LocalTime length, int nrInSeason, PodcastSeason season, boolean isActive) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.creators = creators;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.length = length;
        this.nrInSeason = nrInSeason;
        this.season = season;
        this.isActive = isActive;
    }

    public Podcast(Long id, String title, String url, String description, List<Creator> creators, LocalDate releaseDate, List<Genre> genres, LocalTime length, int nrInSeason, PodcastSeason season, boolean isActive) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.creators = creators;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.length = length;
        this.nrInSeason = nrInSeason;
        this.season = season;
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

    public List<Creator> getCreators() {
        return creators;
    }

    public void setCreators(List<Creator> creators) {
        this.creators = creators;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
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
                ", creators=" + creators +
                ", releaseDate=" + releaseDate +
                ", genres=" + genres +
                ", length=" + length +
                ", nrInSeason=" + nrInSeason +
                ", season=" + season +
                ", isActive=" + isActive +
                '}';
    }

 */
}
