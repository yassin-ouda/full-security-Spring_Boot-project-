package com.EducationSystem.Enrollement.Repository;

import com.EducationSystem.Enrollement.Model.Enrollment;
import com.EducationSystem.Enrollement.Model.EnrollmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

}
