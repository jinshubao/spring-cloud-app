package com.jean.auto.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "t_test_unit")
public class TestUnit extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @JsonIgnore
    @OneToMany(mappedBy = "testUnit")
    private List<TestCase> testCases;

    public List<TestCase> getTestCases() {
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }

    public Module getModule() {
        return module;
    }

    public void setModule(Module module) {
        this.module = module;
    }
}
