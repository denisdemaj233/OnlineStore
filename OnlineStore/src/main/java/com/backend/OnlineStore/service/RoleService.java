package com.backend.OnlineStore.service;

import com.backend.OnlineStore.entity.Role;
import com.backend.OnlineStore.exceptions.ResourceNotFoundException;
import com.backend.OnlineStore.model.RoleDTO;
import com.backend.OnlineStore.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {


    private final RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }

    public RoleDTO toDTO(Role role) {
        if (role == null) {
            return null;
        }
        return new RoleDTO(role.getId(),role.getRoleName());
    }


    public Role toEntity(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRoleName(roleDTO.getRoleName());
        return role;
    }

    public Optional<RoleDTO> findRoleByName(String roleName) {
        Optional<Role> role = Optional.ofNullable(roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("Role with roleName " + roleName + " not found")));
        ;

        return role.map(this::toDTO);

    }
}

