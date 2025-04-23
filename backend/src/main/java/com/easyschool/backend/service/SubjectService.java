package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.Subject;
import com.easyschool.backend.repository.SubjectRepository;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public Subject getSubjectById(Long id) {
        Optional<Subject> subjectOptional = subjectRepository.findById(id);
        if (subjectOptional.isPresent()) {
            return subjectOptional.get();
        } else {
            throw new RuntimeException("Subject not found with id: " + id);
        }
    }

    public Subject saveSubject(Subject newSubject) {
        return subjectRepository.save(newSubject);
    }

    public void deleteSubjectById(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
        } else {
            throw new RuntimeException("Subject not found with id: " + id);
        }
    }

    public Subject updateSubject(Long id, Subject updatedSubject) {
        Optional<Subject> existingSubjectOptional = subjectRepository.findById(id);

        if (!existingSubjectOptional.isPresent()) {
            throw new RuntimeException("Subject not found with id: " + id);
        }

        Subject existingSubject = existingSubjectOptional.get();
        existingSubject.setName(updatedSubject.getName());
        existingSubject.setDescription(updatedSubject.getDescription());

        return subjectRepository.save(existingSubject);
    }
}
