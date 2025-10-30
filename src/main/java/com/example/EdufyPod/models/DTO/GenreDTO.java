package com.example.EdufyPod.models.DTO;

//ED-40-SA
//ED-119-SA
public class GenreDTO {

    private Long genre_id;
    private String name;

    public GenreDTO() {
    }

    public GenreDTO(Long id, String name) {
        this.genre_id = id;
        this.name = name;
    }

    public Long getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(Long genre_id) {
        this.genre_id = genre_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenreDTO{" +
                "id=" + genre_id +
                ", name='" + name + '\'' +
                '}';
    }
}
