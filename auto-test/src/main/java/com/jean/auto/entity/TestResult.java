package com.jean.auto.entity;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "t_test_result")
public class TestResult extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    @OneToOne
    @JoinColumn(name = "api_id")
    private Api api;

    @Column(columnDefinition = "BLOB")
    private String url;

    @Column(columnDefinition = "BLOB")
    private String parameter;

    private Integer statusCode;

    @Column(columnDefinition = "BLOB")
    private String responseBody;

    @Column(columnDefinition = "BLOB")
    private String responseHeader;

    @Column(columnDefinition = "BLOB")
    private String exception;

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
}
