package com.appsbykeegan.backendcrudrestapi.utility;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class MyUtilityClass {

    public String getServerCurrentTime(){

        LocalDateTime timeNow = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return dateTimeFormatter.format(timeNow);
    }

}
