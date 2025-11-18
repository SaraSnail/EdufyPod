package com.example.EdufyPod.clients;

import com.example.EdufyPod.models.enums.MediaType;

//ED-232-SA
public interface ThumbClient {
    void createRecordOfMedia(Long mediaId, MediaType mediaType, String mediaName);
}
