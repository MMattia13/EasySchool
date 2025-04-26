package com.easyschool.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.easyschool.backend.model.Score;
import com.easyschool.backend.payload.response.MessageResponse;
import com.easyschool.backend.service.ScoreService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createScore(@Valid @RequestBody Score score) {
        scoreService.saveScore(score);
        return ResponseEntity.ok(new MessageResponse("Score created successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping
    public ResponseEntity<List<Score>> getAllScores() {
        List<Score> scores = scoreService.getAllScores();
        return ResponseEntity.ok(scores);
    }

    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODERATOR')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getScoreById(@PathVariable Long id) {
        Score score = scoreService.getScoreById(id);
        if (score == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Score not found!"));
        }
        return ResponseEntity.ok(score);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateScore(@PathVariable Long id, @Valid @RequestBody Score updatedScore) {
        Score score = scoreService.updateScore(id, updatedScore);
        if (score == null) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Score not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Score updated successfully!"));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteScore(@PathVariable Long id) {
        try {
            scoreService.deleteScoreById(id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Score not found!"));
        }
        return ResponseEntity.ok(new MessageResponse("Score deleted successfully!"));
    }
}
