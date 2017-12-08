package com.jean.user.api.service;

import com.jean.user.api.entity.TestCase;
import com.jean.user.api.entity.TestUnit;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface ITestService {

    ResponseEntity<Map<String,Object>> executeTestCase(TestCase testCase) throws Exception;

    ResponseEntity<Map<String,Object>> executeTestUnit(TestUnit testUnit) throws Exception;
}
