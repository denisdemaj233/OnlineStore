package com.backend.OnlineStore.repository;

import com.backend.OnlineStore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {


    Optional<Role> findByRoleName(String roleName);
    Role findRoleById(Long roleId);
}
