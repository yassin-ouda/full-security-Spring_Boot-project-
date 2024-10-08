package com.EducationSystem.Enrollement.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CourseDTO {

    private String courseCode;
    private String courseTitle;
    private String category;

    public CourseDTO(String courseCode, String courseTitle) {
        this.courseCode=courseCode;
        this.courseTitle=courseTitle;
    }
}