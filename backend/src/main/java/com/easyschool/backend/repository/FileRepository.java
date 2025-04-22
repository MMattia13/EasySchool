package com.easyschool.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyschool.backend.model.File;

public interface FileRepository extends JpaRepository<File, Long> {
}
