package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.SchoolClass;
import com.easyschool.backend.repository.SchoolClassRepository;

@Service
public class SchoolClassService {

    private final SchoolClassRepository schoolClassRepository;

    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepository) {
        this.schoolClassRepository = schoolClassRepository;
    }

    public List<SchoolClass> getAllClasses() {
        return schoolClassRepository.findAll();
    }

    public SchoolClass getClassById(Long id) {
        Optional<SchoolClass> classOptional = schoolClassRepository.findById(id);
        if (classOptional.isPresent()) {
            return classOptional.get();
        } else {
            throw new RuntimeException("SchoolClass not found with id: " + id);
        }
    }

    public SchoolClass saveClass(SchoolClass newClass) {
        return schoolClassRepository.save(newClass);
    }

    public void deleteClassById(Long id) {
        if (schoolClassRepository.existsById(id)) {
            schoolClassRepository.deleteById(id);
        } else {
            throw new RuntimeException("SchoolClass not found with id: " + id);
        }
    }

    public SchoolClass updateClass(Long id, SchoolClass updatedClass) {
        Optional<SchoolClass> existingClassOptional = schoolClassRepository.findById(id);

        if (!existingClassOptional.isPresent()) {
            throw new RuntimeException("SchoolClass not found with id: " + id);
        }

        SchoolClass existingClass = existingClassOptional.get();
        existingClass.setName(updatedClass.getName());
        existingClass.setDescription(updatedClass.getDescription());

        return schoolClassRepository.save(existingClass);
    }
}
