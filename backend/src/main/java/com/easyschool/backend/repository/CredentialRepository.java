package com.easyschool.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyschool.backend.model.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Long> {}
