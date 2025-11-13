package com.example.EdufyPod.models.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;

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

    //ED-40-SA
    //ED-118-SA
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class CreatorDTO {

        private Long id;
        private String username;

        public CreatorDTO() {
        }

        public CreatorDTO(Long creator_id, String username) {
            this.id = creator_id;
            this.username = username;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return "CreatorDTO{" +
                    "creator_id=" + id +
                    ", username='" + username + '\'' +
                    '}';
        }
    }
}
