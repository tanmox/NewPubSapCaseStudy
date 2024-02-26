package com.sapient.service;

import com.sapient.entity.Movie;
import com.sapient.entity.Theater;
import com.sapient.entity.TheaterMovie;
import com.sapient.repository.MovieRepo;
import com.sapient.repository.TheaterMovieRepo;
import com.sapient.repository.TheaterRepo;
import com.sapient.request.TheaterMovieDeleteRequest;
import com.sapient.request.TheaterMovieRequest;
import com.sapient.request.TheaterMovieUpdateRequest;
import com.sapient.response.TheaterMovieResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
@Slf4j
@Service
public class TheaterMovieServiceImpl implements TheaterMovieService{

    @Autowired
    private TheaterMovieRepo theaterMovieRepo;

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private TheaterRepo theaterRepo;

    @Autowired
    private ModelMapper mapper;

    public List<TheaterMovieResponse> getShowTimes(String city, String movieName) throws Exception {
            log.info("Get Show Time");
            log.info("Movie:"+movieName);
            log.info("City:"+city);

            List<TheaterMovie> theaterMovies = theaterMovieRepo.findByCityAndMovie(movieName, city);
            log.info("Size:"+theaterMovies.size());

            List<TheaterMovieResponse> results = new ArrayList<>();

            theaterMovies.forEach(tm -> {
                TheaterMovieResponse resultObj = new TheaterMovieResponse();
                log.info("Theater ID: " +tm.getTheater_id());
                log.info("Movie ID: " +tm.getMovie_id());
                resultObj.setName(tm.getTheater().getName());
                resultObj.setTm_id(tm.getTmID());
                resultObj.setLocation(tm.getTheater().getLocation());
                resultObj.setZipcode(tm.getTheater().getZipCode());
                resultObj.setShow_time(tm.getShow_time());
                Logger.getLogger("Log get show time").log(Level.INFO, "Showtime" +tm.getShow_time());
                results.add(resultObj);

        });

        return results;
    }

    @Override
    public ArrayList<TheaterMovie> findByTheater(int theaterID) {
        return null;
    }

    @Override
    public ArrayList<TheaterMovie> findByMovie(int movieID) {
        return null;
    }

    @Transactional
    @Override
    public List<TheaterMovieResponse> addMovieByTheater(TheaterMovieRequest tmRequest) throws Exception {
        try {
        log.info("Add Movie By Theater");
        int movieID = movieRepo.findByMovieAndCityName(tmRequest.getMovie_name(), tmRequest.getCity_name()).getMovieId();
        int theaterID = theaterRepo.findByMovieAndCityName(tmRequest.getTheater_name(), tmRequest.getCity_name(), tmRequest.getLocation(), tmRequest.getZip_code()).getId();

        TheaterMovie newEntity = new TheaterMovie();

        Theater theater = theaterRepo.findById(theaterID);
        Movie movie = movieRepo.findByMovieId(movieID);

        newEntity.setTheater(theater);
        newEntity.setMovie(movie);
        newEntity.setMovie_id(movieID);
        newEntity.setTheater_id(theaterID);
        newEntity.setScreen(tmRequest.getScreen());
        newEntity.setShow_time(tmRequest.getShow_time());

        theaterMovieRepo.save(newEntity);

        return getShowTimes(tmRequest.getCity_name(), tmRequest.getMovie_name());
        } catch (Exception e) {
            log.info("Could not update for TM_ID: "+ tmRequest.getTm_ID());
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public ResponseEntity<HttpStatus> deleteMovieByTheater(TheaterMovieDeleteRequest tmDeleteRequest) {

        log.info("Delete Movie By Theater: "+tmDeleteRequest.getTm_ID());
        ResponseEntity<HttpStatus> httpStatusResponseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);

        try {
            theaterMovieRepo.deleteById(tmDeleteRequest.getTm_ID());
            return httpStatusResponseEntity;
        } catch (Exception e) {
            log.info("Could not delete for TM_ID: "+ tmDeleteRequest.getTm_ID());
            throw new EmptyResultDataAccessException(1);
        }

    }

    @Transactional
    public List<TheaterMovieResponse> updateMovieByTheater(TheaterMovieUpdateRequest tmUpdateRequest) {

        TheaterMovie theaterMovie = theaterMovieRepo.findByTmID(tmUpdateRequest.getTm_ID());

        int theaterID = theaterMovie.getTheater_id();
        int movieID = theaterMovie.getMovie_id();
        // Create embedded instance of theater and movie else object will return
        // null pointer as no reference is created

        Theater theater = theaterRepo.findById(theaterID);
        Movie movie = movieRepo.findByMovieId(movieID);
        String cityName = theater.getCity();
        String movieName = movie.getMovieName();

        theaterMovie.setScreen(tmUpdateRequest.getScreen());
        theaterMovie.setShow_time(tmUpdateRequest.getShow_time());

        theaterMovieRepo.save(theaterMovie);
        try {
            return getShowTimes(cityName, movieName);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}