package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.Lesson;
import com.easyschool.backend.repository.LessonRepository;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Lesson getLessonById(Long id) {
        Optional<Lesson> lessonOptional = lessonRepository.findById(id);
        if (lessonOptional.isPresent()) {
            return lessonOptional.get();
        } else {
            throw new RuntimeException("Lesson not found with id: " + id);
        }
    }

    public Lesson saveLesson(Lesson newLesson) {
        return lessonRepository.save(newLesson);
    }

    public void deleteLessonById(Long id) {
        if (lessonRepository.existsById(id)) {
            lessonRepository.deleteById(id);
        } else {
            throw new RuntimeException("Lesson not found with id: " + id);
        }
    }

    public Lesson updateLesson(Long id, Lesson updatedLesson) {
        Optional<Lesson> existingLessonOptional = lessonRepository.findById(id);

        if (!existingLessonOptional.isPresent()) {
            throw new RuntimeException("Lesson not found with id: " + id);
        }

        Lesson existingLesson = existingLessonOptional.get();
        existingLesson.setName(updatedLesson.getName());
        existingLesson.setDescription(updatedLesson.getDescription());
        existingLesson.setDate(updatedLesson.getDate());

        return lessonRepository.save(existingLesson);
    }
}
