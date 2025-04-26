package com.easyschool.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.easyschool.backend.model.SchoolClass;
import com.easyschool.backend.payload.response.MessageResponse;
import com.easyschool.backend.service.SchoolClassService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/school-classes")
public class SchoolClassController {

    @Autowired
    private SchoolClassService schoolClassService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createSchoolClass(@Valid @RequestBody SchoolClass schoolClass) {
        schoolClassService.saveClass(schoolClass);
        return ResponseEntity.ok(new MessageResponse("SchoolClass created successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping
    public ResponseEntity<List<SchoolClass>> getAllSchoolClasses() {
        List<SchoolClass> schoolClasses = schoolClassService.getAllClasses();
        return ResponseEntity.ok(schoolClasses);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getSchoolClassById(@PathVariable Long id) {
        SchoolClass schoolClass = schoolClassService.getClassById(id);
        if (schoolClass == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: SchoolClass not found!"));
        }
        return ResponseEntity.ok(schoolClass);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSchoolClass(@PathVariable Long id, @Valid @RequestBody SchoolClass updatedSchoolClass) {
        SchoolClass schoolClass = schoolClassService.updateClass(id, updatedSchoolClass);
        if (schoolClass == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: SchoolClass not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("SchoolClass updated successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchoolClass(@PathVariable Long id) {
        try {
            schoolClassService.deleteClassById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: SchoolClass not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("SchoolClass deleted successfully!"));
    }
}