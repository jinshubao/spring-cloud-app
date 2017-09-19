package com.jean.auto.repository;

import com.jean.auto.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModuleRepository extends BaseRepository<Module, Long> {

    List<Module> findByProjectId(Long id);
}
