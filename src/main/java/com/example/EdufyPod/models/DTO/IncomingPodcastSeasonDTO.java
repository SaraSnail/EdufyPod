package com.example.EdufyPod.models.DTO;

import java.time.LocalDate;
import java.util.List;

public class IncomingPodcastSeasonDTO {
    private String title;
    private String description;
    private String url;
    private LocalDate releaseDate;
    private List<Long> creatorIds;

    public IncomingPodcastSeasonDTO() {
    }

    public IncomingPodcastSeasonDTO(String title, String description, String url, LocalDate releaseDate, List<Long> creatorIds) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.releaseDate = releaseDate;
        this.creatorIds = creatorIds;
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

    public List<Long> getCreatorIds() {
        return creatorIds;
    }

    public void setCreatorIds(List<Long> creatorIds) {
        this.creatorIds = creatorIds;
    }

    @Override
    public String toString() {
        return "IncomingPodcastSeasonDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                ", releaseDate=" + releaseDate +
                ", creatorIds=" + creatorIds +
                '}';
    }
}
