package com.example.EdufyPod.models.DTO.callDTOs;

//ED-303-SA
public class TransferPodcastDTO {
    private Long mediaId;

    public TransferPodcastDTO() {
    }

    public TransferPodcastDTO(Long mediaId) {
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
        return "TransferPodcastDTO{" +
                "mediaId=" + mediaId +
                '}';
    }
}
