package com.example.EdufyPod.models.DTO;

import java.util.List;

public class PodcastRequest {
    private List<TransferPodcastSeasonDTO> seasonDTOs;
    private List<TransferPodcastDTO> podcastDTOs;

    public PodcastRequest() {
    }

    public PodcastRequest(List<TransferPodcastSeasonDTO> seasonDTOs, List<TransferPodcastDTO> podcastDTOs) {
        this.seasonDTOs = seasonDTOs;
        this.podcastDTOs = podcastDTOs;
    }

    public List<TransferPodcastSeasonDTO> getSeasonDTOs() {
        return seasonDTOs;
    }

    public void setSeasonDTOs(List<TransferPodcastSeasonDTO> seasonDTOs) {
        this.seasonDTOs = seasonDTOs;
    }

    public List<TransferPodcastDTO> getPodcastDTOs() {
        return podcastDTOs;
    }

    public void setPodcastDTOs(List<TransferPodcastDTO> podcastDTOs) {
        this.podcastDTOs = podcastDTOs;
    }

    @Override
    public String toString() {
        return "PodcastRequest{" +
                "seasonDTOs=" + seasonDTOs +
                ", podcastDTOs=" + podcastDTOs +
                '}';
    }
}
