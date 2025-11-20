package com.example.EdufyPod.models.DTO.callDTOs;

//ED-303-SA
public class TransferPodcastDTO {
    private Long id;

    public TransferPodcastDTO() {
    }

    public TransferPodcastDTO(Long id) {
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
        return "TransferPodcastDTO{" +
                "id=" + id +
                '}';
    }
}
