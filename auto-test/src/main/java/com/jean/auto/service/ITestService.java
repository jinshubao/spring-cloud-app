package com.jean.auto.service;

import com.jean.auto.entity.Api;
import com.jean.auto.entity.Parameter;
import com.jean.auto.entity.TestCase;
import com.jean.auto.entity.TestUnit;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITestService {

    ResponseEntity<Map<String,Object>> executeTestCase(TestCase testCase) throws Exception;

    ResponseEntity<Map<String,Object>> executeTestUnit(TestUnit testUnit) throws Exception;
}
