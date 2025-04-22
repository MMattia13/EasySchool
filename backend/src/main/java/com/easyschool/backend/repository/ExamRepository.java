package com.easyschool.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyschool.backend.model.Exam;

public interface ExamRepository extends JpaRepository<Exam, Long> {}
