package com.jean.user.repository;

import com.jean.user.api.entity.TestCase;
import org.springframework.stereotype.Repository;

@Repository
public interface TestCaseRepository extends BaseRepository<TestCase, Long> {

}
