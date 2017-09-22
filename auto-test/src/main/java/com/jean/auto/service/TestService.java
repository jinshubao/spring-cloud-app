package com.jean.auto.service;

import com.jean.auto.constant.CommonConstant;
import com.jean.auto.entity.*;
import com.jean.auto.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
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

    private Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ITestResultService testResultService;

    @Override
    public ResponseEntity<Map<String, Object>> executeTestCase(TestCase testCase) throws Exception {
        Api api = testCase.getApi();
        List<Parameter> parameters = testCase.getParameters();
        List<TestAssert> testAsserts = testCase.getTestAsserts();
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
            parameters.forEach(parameter -> {
                if (CommonConstant.ParameterType.HEADER.getValue().equals(parameter.getParameterType())) {
                    headers.add(parameter.getParameterKey(), parameter.getParameterValue());
                } else if (CommonConstant.ParameterType.BODY.getValue().equals(parameter.getParameterType())) {
                    ruiVars.put(parameter.getParameterKey(), parameter.getParameterValue());
                }
            });
        }
        HttpEntity<Map> httpEntity = new HttpEntity<>(ruiVars, headers);
        ResponseEntity<Map<String, Object>> responseEntity = null;
        int statusCode = 0;
        String responseHeaders = null;
        String responseBody = null;
        String exceptionMessage = null;
        boolean assertResult = true;
        try {
            responseEntity = restTemplate.exchange(url,
                    httpMethod,
                    httpEntity,
                    new ParameterizedTypeReference<Map<String, Object>>() {
                    },
                    ruiVars);
            statusCode = responseEntity.getStatusCodeValue();
            Map<String, Object> entityBody = responseEntity.getBody();
            responseBody = JsonUtils.toJson(entityBody);
            responseHeaders = JsonUtils.toJson(responseEntity.getHeaders());

            //创建SpEL表达式的解析器
            ExpressionParser parser = new SpelExpressionParser();
            StandardEvaluationContext ctx = new StandardEvaluationContext();
            ctx.setVariable("headers", headers);
            ctx.setVariable("response", entityBody);
            if (testAsserts != null) {
                for (TestAssert testAssert : testAsserts) {
                    String expressionString = "#" + testAssert.getAssertKey() + testAssert.getAssertType() + testAssert.getAssertValue();
                    logger.info("expressionString==>{}", expressionString);
                    boolean assertRes = parser.parseExpression(expressionString).getValue(ctx, boolean.class);
                    logger.info("expressionResult==>{}", assertRes);
                    assertResult &= assertRes;
                }
            }

        } catch (RestClientException e) {
            if (e instanceof RestClientResponseException) {
                RestClientResponseException exception = (RestClientResponseException) e;
                statusCode = exception.getRawStatusCode();
                responseBody = exception.getResponseBodyAsString();
                responseHeaders = JsonUtils.toJson(exception.getResponseHeaders());
            }
            exceptionMessage = e.getMessage();
            throw e;
        } finally {
            TestResult result = new TestResult();
            result.setTestCase(testCase);
            result.setCreatedTime(new Date());
            result.setModifiedTime(result.getCreatedTime());
            result.setEnabled(true);
            result.setName(testCase.getName());
            result.setDescription(testCase.getName());
            result.setParameter(JsonUtils.toJson(ruiVars));
            result.setUrl(url);
            result.setStatusCode(statusCode);
            result.setResponseBody(responseBody);
            result.setResponseHeader(responseHeaders);
            result.setException(exceptionMessage);
            result.setAssertResult(assertResult);
            testResultService.save(result);
        }
        return responseEntity;
    }

    @Override
    public ResponseEntity<Map<String, Object>> executeTestUnit(TestUnit testUnit) throws Exception {
        List<TestCase> testCases = testUnit.getTestCases();
        Map<String, ResponseEntity<Map<String, Object>>> result = new HashMap<>();
        for (TestCase testCase : testCases) {
            ResponseEntity<Map<String, Object>> responseEntity = executeTestCase(testCase);
            result.put("", responseEntity);
        }
        //TODO
        return null;
    }
}
