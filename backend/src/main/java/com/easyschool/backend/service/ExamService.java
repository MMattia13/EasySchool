package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.Exam;
import com.easyschool.backend.repository.ExamRepository;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;


    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    
    public Exam getExamById(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        return exam.orElse(null);
    }

    public Exam updateExam(Long id, Exam updatedExam) {
        return examRepository.findById(id).map(exam -> {
            exam.setName(updatedExam.getName());
            exam.setDescription(updatedExam.getDescription());
            exam.setDate(updatedExam.getDate());
            exam.setTime(updatedExam.getTime());
            exam.setDuration(updatedExam.getDuration());
            exam.setType(updatedExam.getType());
            return examRepository.save(exam);
        }).orElse(null);
    }

    public void deleteExamById(Long id) {
        if (examRepository.existsById(id)) {
            examRepository.deleteById(id);
        } else {
            throw new RuntimeException("Exam not found with id: " + id);
        }
    }
}
