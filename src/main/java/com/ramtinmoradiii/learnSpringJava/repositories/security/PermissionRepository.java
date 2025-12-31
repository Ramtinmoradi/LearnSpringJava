package com.ramtinmoradiii.learnSpringJava.repositories.security;

import com.ramtinmoradiii.learnSpringJava.entities.security.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
