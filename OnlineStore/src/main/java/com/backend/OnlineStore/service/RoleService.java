package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Role;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.RoleDTO;
import com.backend.OnlineStore.model.mappers.RoleMapper;
import com.backend.OnlineStore.model.mappers.UserMapper;
import com.backend.OnlineStore.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {


    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleService(RoleRepository roleRepository, UserMapper userMapper, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }


    public Optional<RoleDTO> findRoleByName(String roleName) {
        Optional<Role> role = Optional.ofNullable(roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role with roleName " + roleName + " not found")));;

        return role.map(roleMapper::toDTO);

    }
}

