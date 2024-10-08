package com.EducationSystem.Enrollement.Controllers;

import com.EducationSystem.Enrollement.DTO.CourseDTO;
import com.EducationSystem.Enrollement.DTO.CourseDTODisplayed;
import com.EducationSystem.Enrollement.Services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/savecourse")
    public ResponseEntity<String> createCourse(@RequestBody CourseDTO courseDTO) {
        courseService.createCourse(courseDTO);
        return new ResponseEntity<>("Course created successfully!", HttpStatus.CREATED);
    }

    @GetMapping("/listcourses")
    public List<CourseDTODisplayed> listAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/getcoursebycode/{code}")
    public ResponseEntity<CourseDTODisplayed> getCourseByCode(@PathVariable String code) {
        return new ResponseEntity<>(courseService.getCourseByCode(code), HttpStatus.OK);
    }
    @GetMapping("/getcoursebytitle/{courseTitle}")
    public ResponseEntity<CourseDTODisplayed> getCourseCourseTitle(@PathVariable String courseTitle) {
        return new ResponseEntity<>(courseService.getCourseByTitle(courseTitle), HttpStatus.OK);
    }

    @DeleteMapping("/deletecourse/{code}")
    public ResponseEntity<String> deleteCourse(@PathVariable String code) {
        courseService.deleteCourse(code);
        return new ResponseEntity<>("Course deleted successfully!", HttpStatus.OK);
    }
    @PostMapping("/updatecourse")
    public ResponseEntity<String> updateCourse(@RequestBody CourseDTO courseDTO) {
        try {
            courseService.updateCourse(courseDTO);
            return new ResponseEntity<>("Course updated successfully!", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}