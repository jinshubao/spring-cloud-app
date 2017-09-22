package com.jean.auto.entity;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "t_test_assert")
public class TestAssert extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "test_case_id")
    private TestCase testCase;

    private String assertKey;

    private String assertValue;

    private String assertType;

    public String getAssertKey() {
        return assertKey;
    }

    public void setAssertKey(String assertKey) {
        this.assertKey = assertKey;
    }

    public String getAssertValue() {
        return assertValue;
    }

    public void setAssertValue(String assertValue) {
        this.assertValue = assertValue;
    }

    public String getAssertType() {
        return assertType;
    }

    public void setAssertType(String assertType) {
        this.assertType = assertType;
    }

    public TestCase getTestCase() {
        return testCase;
    }

    public void setTestCase(TestCase testCase) {
        this.testCase = testCase;
    }
}
