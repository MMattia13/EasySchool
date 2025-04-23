package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.File;
import com.easyschool.backend.repository.FileRepository;

@Service
public class FileService {

    private final FileRepository fileRepository;

    @Autowired
    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    public File getFileById(Long id) {
        Optional<File> fileOptional = fileRepository.findById(id);
        if (fileOptional.isPresent()) {
            return fileOptional.get();
        } else {
            throw new RuntimeException("File not found with id: " + id);
        }
    }

    public File saveFile(File newFile) {
        return fileRepository.save(newFile);
    }

    public void deleteFileById(Long id) {
        if (fileRepository.existsById(id)) {
            fileRepository.deleteById(id);
        } else {
            throw new RuntimeException("File not found with id: " + id);
        }
    }

    public File updateFile(Long id, File updatedFile) {
        Optional<File> existingFileOptional = fileRepository.findById(id);

        if (!existingFileOptional.isPresent()) {
            throw new RuntimeException("File not found with id: " + id);
        }

        File existingFile = existingFileOptional.get();
        existingFile.setName(updatedFile.getName());
        existingFile.setType(updatedFile.getType());
        existingFile.setDescription(updatedFile.getDescription());
        existingFile.setDateCreated(updatedFile.getDateCreated());
        existingFile.setDateModified(updatedFile.getDateModified());
        
        return fileRepository.save(existingFile);
    }
}
