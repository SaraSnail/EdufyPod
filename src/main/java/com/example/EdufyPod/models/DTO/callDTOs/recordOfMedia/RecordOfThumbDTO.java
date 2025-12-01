package com.example.EdufyPod.models.DTO.callDTOs.recordOfMedia;

import com.example.EdufyPod.models.enums.MediaType;

//ED-232-SA
public class RecordOfThumbDTO {
    private Long mediaId;
    private MediaType mediaType;
    private String mediaName;

    public RecordOfThumbDTO() {
    }

    public RecordOfThumbDTO(Long mediaId, MediaType mediaType, String mediaName) {
        this.mediaId = mediaId;
        this.mediaType = mediaType;
        this.mediaName = mediaName;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }
}
