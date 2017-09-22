package com.jean.auto.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "t_test_case")
public class TestCase extends BaseEntity {

    private String url;

    private String method;

    private String protocol;

    private String host;

    private Integer port;

    @JsonIgnore
    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL)
    private List<Parameter> parameters;

    @JsonIgnore
    @OneToMany(mappedBy = "testCase")
    private List<TestResult> testResults;

    @JsonIgnore
    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL)
    private List<TestAssert> testAsserts;

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<TestResult> getTestResults() {
        return testResults;
    }

    public void setTestResults(List<TestResult> testResults) {
        this.testResults = testResults;
    }

    public List<TestAssert> getTestAsserts() {
        return testAsserts;
    }

    public void setTestAsserts(List<TestAssert> testAsserts) {
        this.testAsserts = testAsserts;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
