package com.jean.security.repository;

import com.jean.security.entity.SysAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAuthorityRepository extends JpaRepository<SysAuthority, Long> {
}
