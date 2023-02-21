package com.nexcode.security.sb3security6.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nexcode.security.sb3security6.model.entity.Role;
import com.nexcode.security.sb3security6.model.entity.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Optional<Role> findByName(RoleName role);

}
