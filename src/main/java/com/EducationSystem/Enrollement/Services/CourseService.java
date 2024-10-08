package com.EducationSystem.Enrollement.Services;

import com.EducationSystem.Enrollement.DTO.CourseDTO;
import com.EducationSystem.Enrollement.DTO.CourseDTOConverter;
import com.EducationSystem.Enrollement.DTO.CourseDTODisplayed;
import com.EducationSystem.Enrollement.Exception.CourseNotFoundException;
import com.EducationSystem.Enrollement.Model.Course;
import com.EducationSystem.Enrollement.Repository.CourseRepository;
import com.EducationSystem.Enrollement.Response.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course createCourse(CourseDTO courseDTO) {
        Course course = new Course();
        course.setCourseCode(courseDTO.getCourseCode());
        course.setCourseTitle(courseDTO.getCourseTitle());
        course.setCategory(courseDTO.getCategory());

        return courseRepository.save(course);
    }

    public List<CourseDTODisplayed> getAllCourses() {

        return courseRepository.findAll().stream()
                .map(course -> new CourseDTOConverter().Convert(
                        new CourseDTO(course.getCourseCode(), course.getCourseTitle(), course.getCategory())
                ))
                .collect(Collectors.toList());

    }

    public CourseDTODisplayed getCourseByCode(String courseCode) {
        Course course = courseRepository.findById(courseCode)
                .orElseThrow(() -> new CourseNotFoundException(" Requested Denied :Course with the given course code not found not found"));

        return new CourseDTOConverter().Convert( new CourseDTO(course.getCourseCode(), course.getCourseTitle(), course.getCategory()));
    }
    public CourseDTODisplayed getCourseByTitle(String courseTitle) {
        Course course = courseRepository.findBycourseTitle(courseTitle)
                .orElseThrow(() -> new CourseNotFoundException(" Requested Denied :Course with the given course code not found not found"));

        return new CourseDTOConverter().Convert( new CourseDTO(course.getCourseCode(), course.getCourseTitle(), course.getCategory()));
    }

    public void deleteCourse(String code){
         courseRepository.deleteById(code);

    }
    public void updateCourse(CourseDTO courseDTO) {
        Course existingCourse = courseRepository.findById(courseDTO.getCourseCode())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        existingCourse.setCourseTitle(courseDTO.getCourseTitle());
        existingCourse.setCategory(courseDTO.getCategory());

        courseRepository.save(existingCourse);
    }

}
