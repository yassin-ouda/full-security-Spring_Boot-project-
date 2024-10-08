package com.EducationSystem.Enrollement.Controllers;

import com.EducationSystem.Enrollement.Model.Course;
import com.EducationSystem.Enrollement.Model.Enrollment;
import com.EducationSystem.Enrollement.Model.Student;
import com.EducationSystem.Enrollement.Repository.CourseRepository;
import com.EducationSystem.Enrollement.Repository.StudentRepository;
import com.EducationSystem.Enrollement.Services.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")

public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        // Optional: Validate the student and course
        Student student = studentRepository.findById(enrollment.getStudent().getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        Course course = courseRepository.findById(enrollment.getCourse().getCourseCode())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollmentService.saveEnrollment(enrollment);
    }

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentService.getAllEnrollments();
    }
}
