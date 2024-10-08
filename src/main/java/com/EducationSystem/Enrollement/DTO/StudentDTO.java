package com.EducationSystem.Enrollement.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class StudentDTO {
    private Long studentId;

    private String studentName;
    private String studentPhone;
    private List<CourseDTO> courses;

}
