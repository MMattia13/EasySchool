package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.Program;
import com.easyschool.backend.repository.ProgramRepository;

@Service
public class ProgramService {

    @Autowired
    private ProgramRepository programRepository;

    public Program saveProgram(Program program) {
        return programRepository.save(program);
    }

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Program getProgramById(Long id) {
        Optional<Program> program = programRepository.findById(id);
        return program.orElse(null);
    }

    public Program updateProgram(Long id, Program updatedProgram) {
        return programRepository.findById(id).map(program -> {
            program.setName(updatedProgram.getName());
            program.setDescription(updatedProgram.getDescription());
            program.setSchool(updatedProgram.getSchool());
            return programRepository.save(program);
        }).orElse(null);
    }

    public void deleteProgramById(Long id) {
        if (programRepository.existsById(id)) {
            programRepository.deleteById(id);
        } else {
            throw new RuntimeException("Program not found with id: " + id);
        }
    }
}
