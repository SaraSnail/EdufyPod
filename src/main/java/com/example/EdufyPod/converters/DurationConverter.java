package com.example.EdufyPod.converters;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;

//ED-232-SA
public class DurationConverter {

    private DurationConverter(){};

    //ED-232-SA
    public static Duration parsePodcastDuration(String input){
        String[] parts = input.split(":");

        if(parts.length != 3){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid duration format. Expected HH:MM:SS");
        }

        long hours = Long.parseLong(parts[0]);
        long minutes = Long.parseLong(parts[1]);
        long seconds = Long.parseLong(parts[2]);

        return Duration.ofHours(hours)
                .plusMinutes(minutes)
                .plusSeconds(seconds);
    }

    //ED-232-SA
    public static String formatPodcastDuration(Duration duration){
        long seconds = duration.getSeconds();
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;

        return String.format("%d:%02d:%02d", hours, minutes, secs);
    }
}
