package com.jean.auto.repository;

import com.jean.auto.entity.TestCase;
import com.jean.auto.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestResultRepository extends BaseRepository<TestResult, Long> {

}
