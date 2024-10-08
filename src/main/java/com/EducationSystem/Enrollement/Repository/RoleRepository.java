package com.EducationSystem.Enrollement.Repository;

import com.EducationSystem.Enrollement.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role,Integer> {
    Optional<Role> findByName(String name);

}
