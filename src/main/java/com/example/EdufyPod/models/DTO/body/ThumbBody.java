package com.example.EdufyPod.models.DTO.body;

//ED-232-SA
public class ThumbBody {
    private Long mediaId;
    private String mediaType;
    private String mediaName;

    public ThumbBody() {
    }

    public ThumbBody(Long mediaId, String mediaType, String mediaName) {
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }
}
