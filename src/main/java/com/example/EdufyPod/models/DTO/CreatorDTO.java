package com.example.EdufyPod.models.DTO;

//ED-40-SA
//ED-118-SA
public class CreatorDTO {

    private Long creator_id;
    private String username;

    public CreatorDTO() {
    }

    public CreatorDTO(Long creator_id, String username) {
        this.creator_id = creator_id;
        this.username = username;
    }

    public Long getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(Long creator_id) {
        this.creator_id = creator_id;
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
                "creator_id=" + creator_id +
                ", username='" + username + '\'' +
                '}';
    }
}
