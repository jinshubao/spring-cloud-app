package com.jean.auto.repository;

import com.jean.auto.entity.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepository extends BaseRepository<Parameter, Long> {

    List<Parameter> findByApiId(Long id);

    List<Parameter> findByTestCaseIdAndApiId(Long testCaseId, Long apiId);
}
