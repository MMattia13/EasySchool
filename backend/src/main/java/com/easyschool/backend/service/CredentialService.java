package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.Credential;
import com.easyschool.backend.repository.CredentialRepository;

@Service
public class CredentialService {

    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    public List<Credential> getAllCredentials() {
        return credentialRepository.findAll();
    }

    public Credential getCredentialById(Long id) {
        Optional<Credential> credentialOptional = credentialRepository.findById(id);
        if (credentialOptional.isPresent()) {
            return credentialOptional.get();
        } else {
            throw new RuntimeException("Credential not found with id: " + id);
        }
    }

    public Credential saveCredential(Credential newCredential) {
        return credentialRepository.save(newCredential);
    }

    public void deleteCredentialById(Long id) {
        if (credentialRepository.existsById(id)) {
            credentialRepository.deleteById(id);
        } else {
            throw new RuntimeException("Credential not found with id: " + id);
        }
    }

    public Credential updateCredential(Long id, Credential updatedCredential) {
        Optional<Credential> existingCredentialOptional = credentialRepository.findById(id);

        if (!existingCredentialOptional.isPresent()) {
            throw new RuntimeException("Credential not found with id: " + id);
        }

        if (!updatedCredential.getEmail().equals(existingCredentialOptional.get().getEmail())) {
            existingCredentialOptional.get().setEmail(updatedCredential.getEmail());
        }

        return credentialRepository.save(existingCredentialOptional.get());
    }

    public boolean existByEmail(String email) {
        return credentialRepository.existsByEmail(email);
    }
}
