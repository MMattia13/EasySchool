package com.easyschool.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyschool.backend.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
