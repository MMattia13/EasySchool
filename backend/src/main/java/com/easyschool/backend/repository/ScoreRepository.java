package com.easyschool.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyschool.backend.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long> {
}
