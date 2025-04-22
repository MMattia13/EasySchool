package com.easyschool.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyschool.backend.model.Program;

public interface ProgramRepository extends JpaRepository<Program, Long> {}
