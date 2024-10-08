package com.EducationSystem.Enrollement.Exception;

import org.springframework.http.HttpStatus;

public class CourseException {
    private final String message;
    private final Throwable  Throwable;
    private final HttpStatus HttpStatus;

    public CourseException(String message, java.lang.Throwable throwable, org.springframework.http.HttpStatus httpStatus) {
        this.message = message;
        Throwable = throwable;
        HttpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public java.lang.Throwable getThrowable() {
        return Throwable;
    }

    public org.springframework.http.HttpStatus getHttpStatus() {
        return HttpStatus;
    }
}
