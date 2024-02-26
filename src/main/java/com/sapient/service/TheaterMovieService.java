package com.sapient.service;

import com.sapient.entity.TheaterMovie;
import com.sapient.request.TheaterMovieDeleteRequest;
import com.sapient.request.TheaterMovieRequest;
import com.sapient.request.TheaterMovieUpdateRequest;
import com.sapient.response.MenuResponse;
import com.sapient.response.TheaterMovieResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public interface TheaterMovieService {
    public List<TheaterMovieResponse> getShowTimes(String city, String movie) throws Exception;

    public ArrayList<TheaterMovie> findByTheater(int theaterID);

    public ArrayList<TheaterMovie> findByMovie(int movieID);

    public List<TheaterMovieResponse> addMovieByTheater(TheaterMovieRequest tmRequest) throws Exception;
    public List<TheaterMovieResponse> updateMovieByTheater(TheaterMovieUpdateRequest tmUpdateRequest);
    public ResponseEntity<HttpStatus> deleteMovieByTheater(TheaterMovieDeleteRequest tmDeleteRequest);
}
