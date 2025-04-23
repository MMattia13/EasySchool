package com.easyschool.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easyschool.backend.model.User;
import com.easyschool.backend.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public User saveUser(User newUser) {
        return userRepository.save(newUser);
    }

    public void deleteUserById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);

        if (!existingUserOptional.isPresent()) {
            throw new RuntimeException("User not found with id: " + id);
        }

        User existingUser = existingUserOptional.get();
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setBirthdate(updatedUser.getBirthdate());
        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }
}
