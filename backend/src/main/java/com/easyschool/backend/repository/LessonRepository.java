package com.easyschool.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyschool.backend.model.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
}
