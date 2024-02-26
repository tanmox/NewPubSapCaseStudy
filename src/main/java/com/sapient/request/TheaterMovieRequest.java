package com.sapient.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class TheaterMovieRequest {

    private int tm_ID;

    @NonNull
    private String theater_name;

    @NonNull
    private String movie_name;

    @NonNull
    private String city_name;

    @NonNull
    private String location;

    @NonNull
    private String zip_code;

    private int screen;

    @NonNull
    private String show_time;
}
