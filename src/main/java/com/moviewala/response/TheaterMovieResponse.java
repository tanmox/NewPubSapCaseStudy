package com.sapient.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonFormat
public class TheaterMovieResponse {

    private String name;
    private int tm_id;
    private String location;
    private String zipcode;
    private String show_time;
}
