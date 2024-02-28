package com.sapient.errorHandling;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TheaterMovieNotFoundException extends RuntimeException {

    private final String errorCode;

    public TheaterMovieNotFoundException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}