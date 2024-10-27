package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Role;
import com.backend.OnlineStore.model.RoleDTO;
import com.backend.OnlineStore.model.mappers.RoleMapper;
import com.backend.OnlineStore.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {


    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Optional<RoleDTO> findRoleByName(String roleName) {
        Optional<Role> role = roleRepository.findByRoleName(roleName);

        return role.map(RoleMapper.INSTANCE::roleToRoleDTO);
    }
}

