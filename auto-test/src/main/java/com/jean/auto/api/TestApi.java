package com.jean.auto.api;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.Api;
import com.jean.auto.entity.Parameter;
import com.jean.auto.entity.TestCase;
import com.jean.auto.model.common.ApiSimpleResultHelper;
import com.jean.auto.service.IApiService;
import com.jean.auto.service.IParameterService;
import com.jean.auto.service.ITestCaseService;
import com.jean.auto.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/test", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TestApi extends BaseApi {

    @Autowired
    ITestService testService;

    @Autowired
    ITestCaseService testCaseService;

    @Autowired
    IApiService apiService;

    @Autowired
    IParameterService parameterService;

    @GetMapping("/execute")
    ApiSimpleResultHelper<ResponseEntity<Map<String, Object>>> execute(@RequestParam("test_case_id") Long testCaseId) throws Exception {
        TestCase testCase = testCaseService.findOne(testCaseId);
        if (testCase == null) {
            return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.PARAMETER_ERROR, null);
        }
        Api api = testCase.getApi();
        List<Parameter> parameters = parameterService.findByTestCaseIdAndApiId(testCaseId, api.getId());
        ResponseEntity<Map<String, Object>> responseEntity = testService.executeTest(testCase, api, parameters);
        return new ApiSimpleResultHelper<>(CommonConstant.ApiResponse.SUCCESS, responseEntity);

    }
}
