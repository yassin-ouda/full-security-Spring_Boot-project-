package com.EducationSystem.Enrollement.Repository;

import com.EducationSystem.Enrollement.Model.UserEnitity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEnitity,Integer> {
    Optional<UserEnitity>findByUsername(String username);
    Boolean existsByUsername(String username);

}
