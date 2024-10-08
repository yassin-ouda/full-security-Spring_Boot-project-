package com.EducationSystem.Enrollement.Repository;

import com.EducationSystem.Enrollement.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
    Optional<Course> findBycourseTitle(String courseTitle);

}
