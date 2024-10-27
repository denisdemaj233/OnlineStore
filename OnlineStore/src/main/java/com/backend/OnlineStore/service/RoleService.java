package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Role;
import com.backend.OnlineStore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;


    public Optional<Role> findRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}

