package com.example.EdufyPod.models.DTO.body;

import java.util.List;

//ED-232-SA
public class CreatorBody {

    private Long mediaId;
    private String mediaType;
    private List<Long> creatorIds;

    public CreatorBody() {
    }

    public CreatorBody(Long mediaId, String mediaType, List<Long> creatorIds) {
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public List<Long> getCreatorIds() {
        return creatorIds;
    }

    public void setCreatorIds(List<Long> creatorIds) {
        this.creatorIds = creatorIds;
    }
}
