package com.example.EdufyPod.models.DTO.callDTOs;

//ED-303-SA
public class TransferPodcastSeasonDTO {
    private Long id;
    private String title;

    public TransferPodcastSeasonDTO() {
    }

    public TransferPodcastSeasonDTO(Long id, String title) {
        this.id = id;
        this.title = title;
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

    @Override
    public String toString() {
        return "TransferPodcastSeasonDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
