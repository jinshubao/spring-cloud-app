package com.jean.security.repository;

import com.jean.security.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {
}
