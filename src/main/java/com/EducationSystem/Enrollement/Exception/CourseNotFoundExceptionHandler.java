package com.EducationSystem.Enrollement.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CourseNotFoundExceptionHandler {
    @ExceptionHandler(value = CourseNotFoundException.class)
    public ResponseEntity<Object>handleCourseNotFoundException(CourseNotFoundException CourseNotFoundException ){
        CourseException CourseException =new CourseException(CourseNotFoundException.getMessage(),CourseNotFoundException.getCause(), HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(CourseException,HttpStatus.NOT_FOUND);

    }

}
