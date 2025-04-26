package com.easyschool.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.easyschool.backend.model.School;
import com.easyschool.backend.payload.response.MessageResponse;
import com.easyschool.backend.service.SchoolService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createSchool(@Valid @RequestBody School school) {
        schoolService.saveSchool(school);
        return ResponseEntity.ok(new MessageResponse("School created successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MODERATOR')")
    @GetMapping
    public ResponseEntity<List<School>> getAllSchools() {
        List<School> schools = schoolService.getAllSchools();
        return ResponseEntity.ok(schools);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getSchoolById(@PathVariable Long id) {
        School school = schoolService.getSchoolById(id);
        if (school == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: School not found!"));
        }
        return ResponseEntity.ok(school);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchool(@PathVariable Long id, @Valid @RequestBody School updatedSchool) {
        School school = schoolService.updateSchool(id, updatedSchool);
        if (school == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: School not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("School updated successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchool(@PathVariable Long id) {
        try {
            schoolService.deleteSchoolById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: School not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("School deleted successfully!"));
    }
}
