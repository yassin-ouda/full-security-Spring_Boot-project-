package com.EducationSystem.Enrollement.Controllers;

import com.EducationSystem.Enrollement.DTO.StudentDTO;
import com.EducationSystem.Enrollement.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/savestudent")
    public ResponseEntity<String> createStudent(@RequestBody StudentDTO studentDTO) {
        studentService.createStudent(studentDTO);
        return new ResponseEntity<>("Student created successfully!", HttpStatus.CREATED);
    }

    @GetMapping("liststudents")
    public List<StudentDTO> listAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/getstudent/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id)
                .map(student -> new ResponseEntity<>(student, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("deletestudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Student deleted successfully!", HttpStatus.OK);
    }
    @PostMapping("/updatestudent")
    public ResponseEntity<String> updateStudent(@RequestBody StudentDTO studentDTO) {
        try {
            studentService.updateStudent(studentDTO);
            return new ResponseEntity<>("Student updated successfully!", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
