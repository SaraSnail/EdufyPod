package com.example.EdufyPod.models.DTO.body;

import java.util.List;

public class GenreBody {

    private Long mediaId;
    private String mediaType;
    private List<Long> genreIds;

    public GenreBody() {
    }

    public GenreBody(Long mediaId, String mediaType, List<Long> genreIds) {
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
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
