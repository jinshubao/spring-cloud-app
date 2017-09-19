package com.jean.auto.entity;


import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicUpdate
@Table(name = "t_test_case")
public class TestCase extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "test_unit_id")
    private TestUnit testUnit;

    @OneToOne
    @JoinColumn(name = "api_id")
    private Api api;

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
}
