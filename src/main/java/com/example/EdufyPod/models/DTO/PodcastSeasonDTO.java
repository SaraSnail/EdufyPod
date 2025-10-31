package com.example.EdufyPod.models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PodcastSeasonDTO {
    private Long id;
    private String title;
    private String url;
    private String description;
    private List<String> creators;
    private LocalDate releaseDate;
    private List<String> genres;
    private Integer episodeCount;
    private List<PodcastDTO> episodes;

    public PodcastSeasonDTO() {
    }


    public PodcastSeasonDTO(Long id, String title, String url, String description, List<String> creators, LocalDate releaseDate, List<String> genres, Integer episodeCount, List<PodcastDTO> episodes) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.creators = creators;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.episodeCount = episodeCount;
        this.episodes = episodes;
    }

    public PodcastSeasonDTO(String title, String url, String description, List<String> creators, LocalDate releaseDate, List<String> genres, Integer episodeCount, List<PodcastDTO> episodes) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.creators = creators;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.episodeCount = episodeCount;
        this.episodes = episodes;
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

    public List<PodcastDTO> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<PodcastDTO> episodes) {
        this.episodes = episodes;
    }

    @Override
    public String toString() {
        return "PodcastSeasonDTO{" +
                "id=" + id +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", creators=" + creators +
                ", releaseDate=" + releaseDate +
                ", genres=" + genres +
                ", episodeCount=" + episodeCount +
                ", episodes=" + episodes +
                '}';
    }
}
