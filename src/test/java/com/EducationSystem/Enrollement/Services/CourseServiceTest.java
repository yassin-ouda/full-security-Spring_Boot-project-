package com.EducationSystem.Enrollement.Services;

import com.EducationSystem.Enrollement.DTO.CourseDTO;
import com.EducationSystem.Enrollement.Model.Course;
import com.EducationSystem.Enrollement.Repository.CourseRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.result.StatusResultMatchersExtensionsKt.isEqualTo;


class CourseServiceTest {
    @Mock
    private CourseRepository courseRepo;
    @InjectMocks
    private CourseService courseService;
    AutoCloseable autoCloseable;
    CourseDTO courseDTo;
    Course course;




    @BeforeEach
    void setUp() {
        autoCloseable= MockitoAnnotations.openMocks(this);
        courseDTo =new CourseDTO("AI105","Machine Learning","AI");
        course=new Course();
        course.setCourseCode(courseDTo.getCourseCode());
        course.setCourseTitle(courseDTo.getCourseTitle());
        course.setCategory(courseDTo.getCategory());




    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void TestcreateCourse() {
        mock(CourseRepository.class);
        mock(CourseDTO.class);
        when(courseRepo.save(course)).thenReturn(course);
        assertThat(courseService.createCourse(courseDTo)).isEqualTo(course);




    }

    @Test
    void getAllCourses() {
        mock(CourseRepository.class);
        mock(Course.class);
        mock(CourseDTO.class);
        when(courseRepo.findAll()).thenReturn(new ArrayList<Course>(Collections.singletonList(course)));
        assertThat(courseService.getAllCourses().get(0).getCourseCode()).isEqualTo(course.getCourseCode());



    }

    @Test
    void getCourseByCode() {

        when(courseRepo.findById("AI105")).thenReturn(Optional.ofNullable(course));
        assertThat(courseService.getCourseByCode("AI105").getCourseTitle()).isEqualTo(course.getCourseTitle());


    }

    @Test
    void getCourseByTitle() {
        when(courseRepo.findBycourseTitle("Machine Learning")).thenReturn(Optional.ofNullable(course));
        assertThat(courseService.getCourseByTitle("Machine Learning").getCourseCode()).isEqualTo(course.getCourseCode());

    }

    @Test
    void deleteCourse() {
        courseService.deleteCourse("AI105");
        verify(courseRepo, times(1)).deleteById("AI105");


    }

    @Test
    void updateCourse_Success() {
        // Arrange
        CourseDTO courseDTO = new CourseDTO("CS101", "Updated Course Title", "New Category");
        Course existingCourse = new Course();
        existingCourse.setCourseCode(courseDTO.getCourseCode());
        existingCourse.setCourseTitle(courseDTO.getCourseTitle());
        existingCourse.setCategory(courseDTO.getCategory());




        // Mock the repository to return the existing course when findById is called
        when(courseRepo.findById("CS101")).thenReturn(Optional.of(existingCourse));

        // Act
        courseService.updateCourse(courseDTO);

        // Assert
        assertEquals("Updated Course Title", existingCourse.getCourseTitle());
        assertEquals("New Category", existingCourse.getCategory());

        // Verify that the save method was called
        verify(courseRepo).save(existingCourse);
    }

}