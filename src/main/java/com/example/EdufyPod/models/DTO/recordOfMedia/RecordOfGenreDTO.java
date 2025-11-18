package com.example.EdufyPod.models.DTO.recordOfMedia;

import com.example.EdufyPod.models.enums.MediaType;

import java.util.List;

//ED-232-SA
public class RecordOfGenreDTO {

    private Long mediaId;
    private MediaType mediaType;
    private List<Long> genreIds;

    public RecordOfGenreDTO() {
    }

    public RecordOfGenreDTO(Long mediaId, MediaType mediaType, List<Long> genreIds) {
        this.mediaId = mediaId;
        this.mediaType = mediaType;
        this.genreIds = genreIds;
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

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    @Override
    public String toString() {
        return "GenreBody{" +
                "mediaId=" + mediaId +
                ", mediaType='" + mediaType + '\'' +
                ", genreIds=" + genreIds +
                '}';
    }
}
