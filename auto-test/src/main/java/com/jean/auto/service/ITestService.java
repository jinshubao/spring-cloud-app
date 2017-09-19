package com.jean.auto.service;

import com.jean.auto.entity.Api;
import com.jean.auto.entity.Parameter;
import com.jean.auto.entity.TestCase;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ITestService {

    public ResponseEntity<Map<String, Object>> executeTest(TestCase testCase, Api api, List<Parameter> parameters) throws Exception;

}
