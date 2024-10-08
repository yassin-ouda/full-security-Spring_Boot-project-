package com.EducationSystem.Enrollement.Services;

import com.EducationSystem.Enrollement.DTO.CourseDTO;
import com.EducationSystem.Enrollement.DTO.StudentDTO;
import com.EducationSystem.Enrollement.Model.Course;
import com.EducationSystem.Enrollement.Model.Enrollment;
import com.EducationSystem.Enrollement.Model.Student;
import com.EducationSystem.Enrollement.Repository.CourseRepository;
import com.EducationSystem.Enrollement.Repository.EnrollmentRepository;
import com.EducationSystem.Enrollement.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Student createStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setStudentName(studentDTO.getStudentName());
        student.setStudentPhone(studentDTO.getStudentPhone());

        for (CourseDTO courseCode : studentDTO.getCourses()) {
            Course course = courseRepository.findById(courseCode.getCourseCode())
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(course);
            student.getEnrollments().add(enrollment);
        }

        return studentRepository.save(student);
    }
    public void deleteStudent(long id){
         studentRepository.deleteById(id);
    }
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(student -> {
            List<CourseDTO> courseDTOs = student.getEnrollments().stream()
                    .map(enrollment -> new CourseDTO(enrollment.getCourse().getCourseCode(),
                            enrollment.getCourse().getCourseTitle(),
                            enrollment.getCourse().getCategory()))  // Map courses to CourseDTO
                    .collect(Collectors.toList());

            return new StudentDTO(student.getStudentId(),  // Include studentId
                    student.getStudentName(),
                    student.getStudentPhone(),
                    courseDTOs);
        }).collect(Collectors.toList());
    }

    public Optional<StudentDTO> getStudentById(long id) {
        return studentRepository.findById(id).map(student -> {
            List<CourseDTO> courseDTOs = student.getEnrollments().stream()
                    .map(enrollment -> new CourseDTO(enrollment.getCourse().getCourseCode(),
                            enrollment.getCourse().getCourseTitle(),
                            enrollment.getCourse().getCategory()))  // Map courses to CourseDTO
                    .collect(Collectors.toList());

            return new StudentDTO(student.getStudentId(),  // Include studentId
                    student.getStudentName(),
                    student.getStudentPhone(),
                    courseDTOs);
        });
    }
    public void updateStudent(StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(studentDTO.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Clear existing enrollments (courses)
        existingStudent.getEnrollments().clear();

        // Add new or existing courses
        for (CourseDTO courseDTO : studentDTO.getCourses()) {
            Course course = courseRepository.findById(courseDTO.getCourseCode())
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(existingStudent);
            enrollment.setCourse(course);
            existingStudent.getEnrollments().add(enrollment);
        }

        // Save the updated student with modified enrollments
        studentRepository.save(existingStudent);
    }




}