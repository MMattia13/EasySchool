package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.Score;
import com.easyschool.backend.repository.ScoreRepository;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    @Autowired
    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public List<Score> getAllScores() {
        return scoreRepository.findAll();
    }

    public Score getScoreById(Long id) {
        Optional<Score> scoreOptional = scoreRepository.findById(id);
        if (scoreOptional.isPresent()) {
            return scoreOptional.get();
        } else {
            throw new RuntimeException("Score not found with id: " + id);
        }
    }

    public Score saveScore(Score newScore) {
        return scoreRepository.save(newScore);
    }

    public void deleteScoreById(Long id) {
        if (scoreRepository.existsById(id)) {
            scoreRepository.deleteById(id);
        } else {
            throw new RuntimeException("Score not found with id: " + id);
        }
    }

    public Score updateScore(Long id, Score updatedScore) {
        Optional<Score> existingScoreOptional = scoreRepository.findById(id);

        if (!existingScoreOptional.isPresent()) {
            throw new RuntimeException("Score not found with id: " + id);
        }

        Score existingScore = existingScoreOptional.get();
        existingScore.setValue(updatedScore.getValue());

        return scoreRepository.save(existingScore);
    }
}
