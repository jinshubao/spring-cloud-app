package com.jean.auto.repository;

import com.jean.auto.entity.TestCase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends BaseRepository<TestCase, Long> {

}
