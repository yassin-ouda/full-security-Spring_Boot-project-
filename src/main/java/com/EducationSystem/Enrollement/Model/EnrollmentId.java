package com.EducationSystem.Enrollement.Model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EnrollmentId implements Serializable {

    private Long studentId;
    private String courseCode;
}
