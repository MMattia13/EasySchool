package com.easyschool.backend.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.easyschool.backend.model.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByCode(String cod); 
}
