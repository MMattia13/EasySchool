package com.easyschool.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.easyschool.backend.model.Exam;
import com.easyschool.backend.payload.response.MessageResponse;
import com.easyschool.backend.service.ExamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createExam(@Valid @RequestBody Exam exam) {
        examService.saveExam(exam);
        return ResponseEntity.ok(new MessageResponse("Exam created successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        List<Exam> exams = examService.getAllExams();
        return ResponseEntity.ok(exams);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getExamById(@PathVariable Long id) {
        Exam exam = examService.getExamById(id);
        if (exam == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Exam not found!"));
        }
        return ResponseEntity.ok(exam);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateExam(@PathVariable Long id, @Valid @RequestBody Exam updatedExam) {
        Exam exam = examService.updateExam(id, updatedExam);
        if (exam == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Exam not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Exam updated successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExam(@PathVariable Long id) {
        try {
            examService.deleteExamById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Exam not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Exam deleted successfully!"));
    }
}