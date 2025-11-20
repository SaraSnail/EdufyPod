package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.DTO.callDTOs.UserDTO;

//ED-283-SA
public interface UserClient {

    void validateUserById(Long userId);//ED-283-SA
    UserDTO getUserBySUB(String sub);
}
