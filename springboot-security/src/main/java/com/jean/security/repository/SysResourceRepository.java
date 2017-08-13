package com.jean.security.repository;

import com.jean.security.entity.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysResourceRepository extends JpaRepository<SysResource, Long> {
}
