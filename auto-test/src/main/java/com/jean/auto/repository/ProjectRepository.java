package com.jean.auto.repository;

import com.jean.auto.entity.Project;
import com.jean.auto.model.ProjectNameModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends BaseRepository<Project, Long> {

    @Query("select new com.jean.auto.model.ProjectNameModel(p.id, p.name) from Project p")
    List<ProjectNameModel> findAllProjectNames();
}
