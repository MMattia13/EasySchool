package com.easyschool.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.easyschool.backend.model.Program;
import com.easyschool.backend.payload.response.MessageResponse;
import com.easyschool.backend.service.ProgramService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramService programService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createProgram(@Valid @RequestBody Program program) {
        programService.saveProgram(program);
        return ResponseEntity.ok(new MessageResponse("Program created successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping
    public ResponseEntity<List<Program>> getAllPrograms() {
        List<Program> programs = programService.getAllPrograms();
        return ResponseEntity.ok(programs);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getProgramById(@PathVariable Long id) {
        Program program = programService.getProgramById(id);
        if (program == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Program not found!"));
        }
        return ResponseEntity.ok(program);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProgram(@PathVariable Long id, @Valid @RequestBody Program updatedProgram) {
        Program program = programService.updateProgram(id, updatedProgram);
        if (program == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Program not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Program updated successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProgram(@PathVariable Long id) {
        try {
            programService.deleteProgramById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Program not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Program deleted successfully!"));
    }
}
