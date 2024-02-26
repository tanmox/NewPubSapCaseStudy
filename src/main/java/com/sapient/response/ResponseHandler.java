package com.sapient.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {
    public static ResponseEntity<MenuResponse> generateResponse(String message, String city, String movie, int trackinID, Date date, List<TheaterMovieResponse> results) {
        MenuResponse menu = new MenuResponse();
        menu.setCityName(city);
        menu.setMovieName(movie);
        menu.setTheaterMovies(results);
        menu.setTrackingID(trackinID);
        menu.setRequestDate(date);
        return (ResponseEntity<MenuResponse>) ResponseEntity.ok(menu);
    }


    public static ResponseEntity<MenuResponse> generateResponse(String message, int trackinID, Date date, List<TheaterMovieResponse> results) {
        MenuResponse menu = new MenuResponse();
        menu.setTheaterMovies(results);
        menu.setTrackingID(trackinID);
        menu.setRequestDate(date);
        return (ResponseEntity<MenuResponse>) ResponseEntity.ok(menu);
    }

    public static ResponseEntity<MenuResponse> generateResponse(String responseGenerated, List<TheaterMovieResponse> results) {
        return null;
    }
}
