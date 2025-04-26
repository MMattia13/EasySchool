package com.easyschool.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.easyschool.backend.model.Lesson;
import com.easyschool.backend.payload.response.MessageResponse;
import com.easyschool.backend.service.LessonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createLesson(@Valid @RequestBody Lesson lesson) {
        lessonService.saveLesson(lesson);
        return ResponseEntity.ok(new MessageResponse("Lesson created successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping
    public ResponseEntity<List<Lesson>> getAllLessons() {
        List<Lesson> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getLessonById(@PathVariable Long id) {
        Lesson lesson = lessonService.getLessonById(id);
        if (lesson == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Lesson not found!"));
        }
        return ResponseEntity.ok(lesson);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateLesson(@PathVariable Long id, @Valid @RequestBody Lesson updatedLesson) {
        Lesson lesson = lessonService.updateLesson(id, updatedLesson);
        if (lesson == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Lesson not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Lesson updated successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLesson(@PathVariable Long id) {
        try {
            lessonService.deleteLessonById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Lesson not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Lesson deleted successfully!"));
    }
}
