package com.example.EdufyPod.models.DTO.callDTOs;

//ED-303-SA
public class TransferPodcastSeasonDTO {
    private Long id;

    public TransferPodcastSeasonDTO() {
    }

    public TransferPodcastSeasonDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TransferPodcastSeasonDTO{" +
                "id=" + id +
                '}';
    }
}
