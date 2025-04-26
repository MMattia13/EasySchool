package com.easyschool.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.easyschool.backend.model.Subject;
import com.easyschool.backend.payload.response.MessageResponse;
import com.easyschool.backend.service.SubjectService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createSubject(@Valid @RequestBody Subject subject) {
        subjectService.saveSubject(subject);
        return ResponseEntity.ok(new MessageResponse("Subject created successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return ResponseEntity.ok(subjects);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getSubjectById(@PathVariable Long id) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Subject not found!"));
        }
        return ResponseEntity.ok(subject);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSubject(@PathVariable Long id, @Valid @RequestBody Subject updatedSubject) {
        Subject subject = subjectService.updateSubject(id, updatedSubject);
        if (subject == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Subject not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Subject updated successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long id) {
        try {
            subjectService.deleteSubjectById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Subject not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Subject deleted successfully!"));
    }
}
