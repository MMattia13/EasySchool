package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.School;
import com.easyschool.backend.repository.SchoolRepository;

@Service
public class SchoolService {

    private final SchoolRepository schoolRepository;

    @Autowired
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    public School getSchoolById(Long id) {
        Optional<School> schoolOptional = schoolRepository.findById(id);
        if (schoolOptional.isPresent()) {
            return schoolOptional.get();
        } else {
            throw new RuntimeException("School not found with id: " + id);
        }
    }

    public School saveSchool(School newSchool) {
        return schoolRepository.save(newSchool);
    }

    public void deleteSchoolById(Long id) {
        if (schoolRepository.existsById(id)) {
            schoolRepository.deleteById(id);
        } else {
            throw new RuntimeException("School not found with id: " + id);
        }
    }

    public School updateSchool(Long id, School updatedSchool) {
        Optional<School> existingSchoolOptional = schoolRepository.findById(id);

        if (!existingSchoolOptional.isPresent()) {
            throw new RuntimeException("School not found with id: " + id);
        }

        School existingSchool = existingSchoolOptional.get();
        existingSchool.setName(updatedSchool.getName());
        existingSchool.setDescription(updatedSchool.getDescription());

        return schoolRepository.save(existingSchool);
    }
}
