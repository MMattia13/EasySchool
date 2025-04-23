package com.easyschool.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.easyschool.backend.model.SchoolClass;

public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long> {}
