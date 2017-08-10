package com.jean.security.repository;

import com.jean.security.model.SysRole;
import com.jean.security.model.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Integer> {

    List<SysRole> findByUsers(SysUser... users);
}
