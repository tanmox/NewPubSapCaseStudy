package com.sapient.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
public class TheaterMovieUpdateRequest {

    private int tm_ID;

    private int screen;

    @NonNull
    private String show_time;
}
