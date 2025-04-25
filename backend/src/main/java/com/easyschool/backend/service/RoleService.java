package com.easyschool.backend.service;

import com.easyschool.backend.model.Role;
import com.easyschool.backend.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleByCode(String code) {
        return roleRepository.findByCode(code).orElseThrow(() -> new RuntimeException("Role not found"));
    }

}
