package com.example.EdufyPod.models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;
//ED-76-SA
@JsonInclude(JsonInclude.Include.NON_NULL)//ED-77-SA
public class PodcastSeasonDTO {//ED-77-SA
    private Long id;
    private String title;
    private String url;
    private String description;
    private List<CreatorDTO> creators;
    private LocalDate releaseDate;
    private Boolean active;//ED-82-SA - ED-263-SA:changed to Boolean
    private Integer episodeCount;
    private List<PodcastDTO> episodes;


    public PodcastSeasonDTO() {
    }


    public PodcastSeasonDTO(Long id, String title, String url, String description, List<CreatorDTO> creators, LocalDate releaseDate, Boolean active, Integer episodeCount, List<PodcastDTO> episodes) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.creators = creators;
        this.releaseDate = releaseDate;
        this.active = active;
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
                ", active='" + active + '\'' +
                ", episodeCount=" + episodeCount +
                ", episodes=" + episodes +

                '}';
    }
}
