package com.example.EdufyPod.models.DTO;

import java.time.LocalDate;
import java.util.List;

public class PodcastSeasonDTO {
    private String title;
    private String url;
    private String description;
    private List<String> creators;
    private LocalDate releaseDate;
    private List<String> genres;
    private Integer episodeCount;

    public PodcastSeasonDTO() {
    }

    public PodcastSeasonDTO(String title, String url, String description, List<String> creators, LocalDate releaseDate, List<String> genres, Integer episodeCount) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.creators = creators;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.episodeCount = episodeCount;
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

    public List<String> getCreators() {
        return creators;
    }

    public void setCreators(List<String> creators) {
        this.creators = creators;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getGenres() {
        return genres;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public Integer getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(Integer episodeCount) {
        this.episodeCount = episodeCount;
    }

    @Override
    public String toString() {
        return "PodcastSeasonDTO{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", creators=" + creators +
                ", releaseDate=" + releaseDate +
                ", genres=" + genres +
                ", episodeCount=" + episodeCount +
                '}';
    }
}
