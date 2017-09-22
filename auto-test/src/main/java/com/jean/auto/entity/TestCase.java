package com.jean.auto.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "t_test_case")
public class TestCase extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "test_unit_id")
    private TestUnit testUnit;

    @ManyToOne
    @JoinColumn(name = "api_id")
    private Api api;

    @JsonIgnore
    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL)
    private List<Parameter> parameters;

    @JsonIgnore
    @OneToMany(mappedBy = "testCase")
    private List<TestResult> testResults;

    @JsonIgnore
    @OneToMany(mappedBy = "testCase", cascade = CascadeType.ALL)
    private List<TestAssert> testAsserts;

    public TestUnit getTestUnit() {
        return testUnit;
    }

    public void setTestUnit(TestUnit testUnit) {
        this.testUnit = testUnit;
    }

    public Api getApi() {
        return api;
    }

    public void setApi(Api api) {
        this.api = api;
    }

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
}
