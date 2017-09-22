package com.jean.auto.api;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.TestCase;
import com.jean.auto.entity.TestUnit;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestApi extends BaseApi {

    @Autowired
    private ITestService testService;

    @Autowired
    private ITestCaseService testCaseService;

    private ITestUnitService testUnitService;

    @Autowired
    private IApiService apiService;

    @Autowired
    private IParameterService parameterService;

    @GetMapping("/case/{test_case_id}/execute")
    ApiSimpleResultHelper<ResponseEntity<Map<String, Object>>> executeCase(@PathVariable("test_case_id") Long caseId) throws Exception {
        TestCase testCase = testCaseService.findOne(caseId);
        if (testCase == null) {
            return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.PARAMETER_ERROR, null);
        }
        ResponseEntity<Map<String, Object>> responseEntity = testService.executeTestCase(testCase);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, responseEntity);
    }

    @GetMapping("/unit/{test_unit_id}/execute")
    ApiSimpleResultHelper<ResponseEntity<Map<String, Object>>> executeUnit(@PathVariable("test_unit_id") Long unitId) throws Exception {
        TestUnit object = testUnitService.findOne(unitId);
        if (object == null) {
            return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.PARAMETER_ERROR, null);
        }
        ResponseEntity<Map<String, Object>> result = testService.executeTestUnit(object);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, result);

    }
}
