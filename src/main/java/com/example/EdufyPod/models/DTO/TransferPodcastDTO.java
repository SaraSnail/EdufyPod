package com.example.EdufyPod.models.DTO;

public class TransferPodcastDTO {
    private Long id;
    private String title;

    public TransferPodcastDTO() {
    }

    public TransferPodcastDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "TransferPodcastDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
