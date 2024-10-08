package com.EducationSystem.Enrollement.Repository;

import com.EducationSystem.Enrollement.DTO.CourseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import com.EducationSystem.Enrollement.Repository.CourseRepository;
import com.EducationSystem.Enrollement.Model.Course;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest

public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepo;
    CourseDTO courseDTO;


    Course course =new Course();
    @BeforeEach

    void setup() {
        courseDTO=new CourseDTO("CS111","Medicine","Bio");
        course.setCourseCode(courseDTO.getCourseCode());
        course.setCourseTitle(courseDTO.getCourseTitle());
        course.setCategory(courseDTO.getCategory());
        courseRepo.save(course);

    }
    @AfterEach

    public void teardown() {
        course=null;
        courseRepo.deleteAll();


    }
    @Test
    //success

    public void TestCourseByTitle() {
        Optional<Course> x=courseRepo.findBycourseTitle("Medicine");
        assertThat(x.get().getCourseCode()).isEqualTo(course.getCourseCode());


    }
    //failure


    @Test
    void FailuerTestOnTitle() {
        Optional<Course>y=courseRepo.findBycourseTitle("homaar");
        assertThat(y.isEmpty()).isTrue();

    }
}
