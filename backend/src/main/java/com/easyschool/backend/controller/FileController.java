package com.easyschool.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyschool.backend.model.File;
import com.easyschool.backend.payload.response.MessageResponse;
import com.easyschool.backend.service.FileService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createFile(@Valid @RequestBody File file) {
        fileService.saveFile(file);
        return ResponseEntity.ok(new MessageResponse("File created successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MODERATOR')")
    @GetMapping
    public ResponseEntity<List<File>> getAllFiles() {
        List<File> files = fileService.getAllFiles();
        return ResponseEntity.ok(files);
    }

    @PreAuthorize("hasAuthority('ADMIN')or hasAuthority('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getFileById(@PathVariable Long id) {
        File file = fileService.getFileById(id);
        if (file == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: File not found!"));
        }
        return ResponseEntity.ok(file);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFile(@PathVariable Long id, @Valid @RequestBody File updatedFile) {
        File file = fileService.updateFile(id, updatedFile);
        if (file == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: File not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("File updated successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable Long id) {
        try {
            fileService.deleteFileById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: File not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("File deleted successfully!"));
    }
}
