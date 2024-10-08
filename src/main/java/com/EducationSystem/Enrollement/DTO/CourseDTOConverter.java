package com.EducationSystem.Enrollement.DTO;

public class CourseDTOConverter {
    public CourseDTODisplayed Convert(CourseDTO courseDTO){
        return new CourseDTODisplayed(courseDTO.getCourseCode(), courseDTO.getCourseTitle());
    }
}
