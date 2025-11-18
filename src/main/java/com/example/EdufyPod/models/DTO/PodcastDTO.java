package com.example.EdufyPod.models.DTO;

import com.example.EdufyPod.models.DTO.callDTOs.CreatorDTO;
import com.example.EdufyPod.models.DTO.callDTOs.GenreDTO;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

//ED-76-SA
@JsonInclude(JsonInclude.Include.NON_NULL)//ED-77-SA
public class PodcastDTO {
    private Long id;//ED-77-SA
    private String title;
    private String url;
    private String description;
    private List<CreatorDTO> creators;
    private LocalDate releaseDate;
    private List<GenreDTO> genres;
    private String length;
    private Integer nrInSeason;
    private PodcastSeasonDTO season;
    private Integer timesListened;
    private Boolean active;//ED-82-SA - ED-263-SA:changed to Boolean

    public PodcastDTO() {
    }

    public PodcastDTO(Long id, String title, String url, String description, List<CreatorDTO> creators, LocalDate releaseDate, List<GenreDTO> genres, String length, Integer nrInSeason, PodcastSeasonDTO season, Integer timesListened, Boolean active) {
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
        this.active = active;
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

    public List<CreatorDTO> getCreators() {
        return creators;
    }

    public void setCreators(List<CreatorDTO> creators) {
        this.creators = creators;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<GenreDTO> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreDTO> genres) {
        this.genres = genres;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public Integer getNrInSeason() {
        return nrInSeason;
    }

    public void setNrInSeason(Integer nrInSeason) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
                ", active='" + active + '\'' +
                '}';
    }
}
