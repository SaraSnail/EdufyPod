package com.example.EdufyPod.models.DTO.callDTOs.recordOfMedia;

import com.example.EdufyPod.models.enums.MediaType;

import java.util.List;

//ED-232-SA
public class RecordOfCreatorDTO {

    private Long mediaId;
    private MediaType mediaType;
    private List<Long> creatorIds;

    public RecordOfCreatorDTO() {
    }

    public RecordOfCreatorDTO(Long mediaId, MediaType mediaType, List<Long> creatorIds) {
        this.mediaId = mediaId;
        this.mediaType = mediaType;
        this.creatorIds = creatorIds;
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

    public List<Long> getCreatorIds() {
        return creatorIds;
    }

    public void setCreatorIds(List<Long> creatorIds) {
        this.creatorIds = creatorIds;
    }
}
