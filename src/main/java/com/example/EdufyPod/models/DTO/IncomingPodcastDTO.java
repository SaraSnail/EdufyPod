package com.example.EdufyPod.models.DTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//ED-232-SA
public class IncomingPodcastDTO {
    private String title;
    private String url;
    private String description;
    private List<Long> creatorIds;
    private LocalDate releaseDate;
    private List<Long> genreIds;
    private String length;
    private Integer nrInSeason;
    private Long seasonId;

    public IncomingPodcastDTO(String title, String url, String description, List<Long> creatorIds, LocalDate releaseDate, List<Long> genreIds, String length, Integer nrInSeason, Long seasonId) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.creatorIds = creatorIds;
        this.releaseDate = releaseDate;
        this.genreIds = genreIds;
        this.length = length;
        this.nrInSeason = nrInSeason;
        this.seasonId = seasonId;
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

    public List<Long> getCreatorIds() {
        return creatorIds;
    }

    public void setCreatorIds(List<Long> creatorIds) {
        this.creatorIds = creatorIds;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
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

    public Long getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(Long seasonId) {
        this.seasonId = seasonId;
    }


    @Override
    public String toString() {
        return "IncomingPodcastDTO{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", creatorIds=" + creatorIds +
                ", releaseDate=" + releaseDate +
                ", genreIds=" + genreIds +
                ", length=" + length +
                ", nrInSeason=" + nrInSeason +
                ", seasonId=" + seasonId +
                '}';
    }
}
