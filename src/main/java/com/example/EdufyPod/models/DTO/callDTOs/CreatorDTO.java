package com.example.EdufyPod.models.DTO.callDTOs;

import com.fasterxml.jackson.annotation.JsonInclude;


//ED-40-SA
// ED-118-SA
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatorDTO {
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

