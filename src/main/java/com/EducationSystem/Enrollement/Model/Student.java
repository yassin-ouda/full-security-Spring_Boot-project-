package com.EducationSystem.Enrollement.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student {

    @Id
    private Long studentId;

    private String studentName;
    private String studentPhone;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonManagedReference // Prevent infinite recursion
    private List<Enrollment> enrollments = new ArrayList<>();
}
