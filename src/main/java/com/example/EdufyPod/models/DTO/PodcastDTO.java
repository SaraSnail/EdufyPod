package com.example.EdufyPod.models.DTO;

import com.example.EdufyPod.models.entities.PodcastSeason;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

//ED-76-SA
@JsonInclude(JsonInclude.Include.NON_NULL)//ED-77-SA
public class PodcastDTO {
    private Long id;//ED-77-SA
    private String title;
    private String url;
    private String description;
    private List<String> creators;
    private LocalDate releaseDate;
    private List<String> genres;
    private LocalTime length;
    private int nrInSeason;
    private PodcastSeasonDTO season;
    private Integer timesListened;

    public PodcastDTO() {
    }

    public PodcastDTO(Long id, String title, String url, String description, List<String> creators, LocalDate releaseDate, List<String> genres, LocalTime length, int nrInSeason, PodcastSeasonDTO season, Integer timesListened) {
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
        this.timesListened = timesListened;
    }

    public PodcastDTO(String title, String url, String description, List<String> creators, LocalDate releaseDate, List<String> genres, LocalTime length, int nrInSeason, PodcastSeasonDTO season, Integer timesListened) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.creators = creators;
        this.releaseDate = releaseDate;
        this.genres = genres;
        this.length = length;
        this.nrInSeason = nrInSeason;
        this.season = season;
        this.timesListened = timesListened;
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

    public PodcastSeasonDTO getSeason() {
        return season;
    }

    public void setSeason(PodcastSeasonDTO season) {
        this.season = season;
    }

    public Integer getTimesListened() {
        return timesListened;
    }

    public void setTimesListened(Integer timesListened) {
        this.timesListened = timesListened;
    }

    @Override
    public String toString() {
        return "PodcastDTO{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", creators=" + creators +
                ", releaseDate=" + releaseDate +
                ", genres=" + genres +
                ", length=" + length +
                ", nrInSeason=" + nrInSeason +
                ", season=" + season +
                ", timesListened=" + timesListened +
                '}';
    }
}
