package com.jean.auto.service;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.Api;
import com.jean.auto.entity.Parameter;
import com.jean.auto.entity.TestCase;
import com.jean.auto.entity.TestResult;
import com.jean.auto.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TestService implements ITestService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ITestResultService testResultService;


    @Override
    public ResponseEntity<Map<String, Object>> executeTest(TestCase testCase, Api api, List<Parameter> parameters) throws Exception {
        String url = "";
        if (!StringUtils.isEmpty(api.getProtocol())) {
            url = api.getProtocol() + "://";
        }
        if (!StringUtils.isEmpty(api.getHost())) {
            url += api.getHost();
        }
        if (api.getPort() != null) {
            url += ":" + api.getPort();
        }
        url += api.getUrl();
        Map<String, Object> ruiVars = new HashMap<>();
        HttpMethod httpMethod = HttpMethod.valueOf(api.getMethod().toUpperCase());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        if (parameters != null) {
            for (Parameter parameter : parameters) {
                if (CommonConstant.ParameterType.HEADER.getValue().equals(parameter.getParameterType())) {
                    headers.add(parameter.getParameterKey(), parameter.getParameterValue());
                } else if (CommonConstant.ParameterType.BODY.getValue().equals(parameter.getParameterType())) {
                    ruiVars.put(parameter.getParameterKey(), parameter.getParameterValue());
                }
            }
        }
        HttpEntity<Map> httpEntity = new HttpEntity<>(ruiVars, headers);
        ResponseEntity<Map<String, Object>> responseEntity = null;
        int statusCode = 0;
        String responseHeaders = null;
        String responseBody = null;
        String exceptionMessage = null;
        try {
            responseEntity = restTemplate.exchange(url,
                    httpMethod,
                    httpEntity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    },
                    ruiVars);
            statusCode = responseEntity.getStatusCodeValue();
            responseBody = JsonUtils.toJson(responseEntity.getBody());
            responseHeaders = JsonUtils.toJson(responseEntity.getHeaders());
        } catch (RestClientException e) {
            if (e instanceof RestClientResponseException) {
                RestClientResponseException exception = (RestClientResponseException) e;
                statusCode = exception.getRawStatusCode();
                responseBody = exception.getResponseBodyAsString();
                responseHeaders = JsonUtils.toJson(exception.getResponseHeaders());
            }
            exceptionMessage = e.getMessage();
        } finally {
            TestResult result = new TestResult();
            result.setApi(api);
            result.setTestCase(testCase);
            result.setCreatedTime(new Date());
            result.setModifiedTime(result.getCreatedTime());
            result.setEnabled(true);
            result.setName(testCase.getName());
            result.setDescription(testCase.getName());
            result.setRemark(testCase.getName());
            result.setParameter(JsonUtils.toJson(ruiVars));
            result.setUrl(url);
            result.setStatusCode(statusCode);
            result.setResponseBody(responseBody);
            result.setResponseHeader(responseHeaders);
            result.setException(exceptionMessage);
            testResultService.save(result);
        }
        return responseEntity;
    }
}
