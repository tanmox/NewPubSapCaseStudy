package com.sapient.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuResponse {

    private int trackingID;
    private Date requestDate;
    private String movieName;
    private String cityName;

    private List<TheaterMovieResponse> theaterMovies;
}
