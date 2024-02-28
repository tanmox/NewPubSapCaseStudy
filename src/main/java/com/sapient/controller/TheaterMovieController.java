package com.sapient.controller;

import com.sapient.entity.TheaterMovie;
import com.sapient.errorHandling.TheaterMovieNotFoundException;
import com.sapient.request.TheaterMovieDeleteRequest;
import com.sapient.request.TheaterMovieRequest;
import com.sapient.request.TheaterMovieUpdateRequest;
import com.sapient.response.MenuResponse;
import com.sapient.response.ResponseHandler;
import com.sapient.response.TheaterMovieResponse;
import com.sapient.service.TheaterMovieServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;
@Slf4j
@RestController
 class TheaterMovieController {

    @Autowired
    private TheaterMovieServiceImpl theaterMovieServiceImpl;

    @RequestMapping(value = "/movie/{city}/{movie}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MenuResponse> getMovieDetails(@PathVariable("city") String city, @PathVariable("movie") String movie) throws Exception {
        try {
            log.info("Controller Get Movie Details");
            log.info("City" +city);
            log.info("Movie:"+movie);

            int trackingID = RandomGenerator.getDefault().nextInt();

            Date requestDate = new Date();

            List<TheaterMovieResponse> results = theaterMovieServiceImpl.getShowTimes(city, movie);
            System.out.println("Coming back:"+trackingID);

            if (results.isEmpty()) {
                log.info("No Content");
                throw new TheaterMovieNotFoundException("Theater_Movie_Not_Found", "Movie For City " +movie+"_"+city + " not found");
            }
            else
                return ResponseHandler.generateResponse("Response Generated", city, movie, trackingID, requestDate, results);
        } catch (Exception e) {
            log.info("Exception No Content");
            throw new TheaterMovieNotFoundException("Theater_Movie_Not_Found", "Movie For City " +movie+"_"+city + " not found");
        }

    }

    @PostMapping(path = "/movie/addMovieByTheater", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MenuResponse> addMovieByTheater(@RequestBody TheaterMovieRequest tmRequest) {

        try {
            log.info("Controller Add Movie By Theater");
            int trackingID = RandomGenerator.getDefault().nextInt();

            Date requestDate = new Date();

            List<TheaterMovieResponse> results = theaterMovieServiceImpl.addMovieByTheater(tmRequest);
            log.info("Coming back to Controller:"+trackingID);

            if (results.isEmpty()) {
                log.info("No Content");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else
                return ResponseHandler.generateResponse("Response Generated", tmRequest.getCity_name(), tmRequest.getMovie_name(), trackingID, requestDate, results);
        } catch (Exception e) {
            log.info("Exception No Content");
            throw new TheaterMovieNotFoundException("Theater_Movie_Not_Found", "Movie For City " +tmRequest.getMovie_name()+"_"+tmRequest.getCity_name() + " not found");
        }
    }

    @PutMapping(path = "/movie/updateMovieByTheater", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MenuResponse> updateMovieByTheater(@RequestBody TheaterMovieUpdateRequest tmUpdateRequest) {

        try {
            log.info("Controller Update Movie By Theater");
            int trackingID = RandomGenerator.getDefault().nextInt();

            Date requestDate = new Date();

            List<TheaterMovieResponse> results = theaterMovieServiceImpl.updateMovieByTheater(tmUpdateRequest);
            log.info("Coming back to Controller:"+trackingID);

            if (results.isEmpty()) {
                log.info("No Content");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            else
                return ResponseHandler.generateResponse("Response Generated", trackingID, requestDate, results);
        } catch (Exception e) {
            log.info("Exception No Content");
            throw new TheaterMovieNotFoundException("Theater_Movie_Not_Found", "TM_ID " +tmUpdateRequest.getTm_ID() + " not found");
        }
    }

    @DeleteMapping("/movie/deleteMovieByTheater")
    public ResponseEntity<HttpStatus> deleteMovieByTheater(@RequestBody TheaterMovieDeleteRequest tmDelRequest) {

        try {
            log.info("Controller Delete Movie By Theater");
            theaterMovieServiceImpl.deleteMovieByTheater(tmDelRequest);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            log.info("Exception No Content");
            throw new TheaterMovieNotFoundException("Theater_Movie_Not_Found", "TM_ID " +tmDelRequest.getTm_ID() + " not found");
        }
    }


}


