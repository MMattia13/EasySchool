package com.easyschool.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.easyschool.backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
