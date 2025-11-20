package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.DTO.callDTOs.UserDTO;

//ED-283-SA
public interface UserClient {

    //ED-283-SA
    UserDTO getUserById(Long userIds);
    UserDTO getUserBySUB(String sub);
}
