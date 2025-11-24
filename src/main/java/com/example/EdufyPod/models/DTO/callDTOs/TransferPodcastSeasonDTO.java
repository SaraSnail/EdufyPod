package com.example.EdufyPod.models.DTO.callDTOs;

//ED-303-SA
public class TransferPodcastSeasonDTO {
    private Long mediaId;

    public TransferPodcastSeasonDTO() {
    }

    public TransferPodcastSeasonDTO(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "TransferPodcastSeasonDTO{" +
                "mediaId=" + mediaId +
                '}';
    }
}
