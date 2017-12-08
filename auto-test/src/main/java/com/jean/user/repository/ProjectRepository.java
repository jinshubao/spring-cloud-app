package com.jean.user.repository;

import com.jean.user.api.entity.Project;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> {

}
