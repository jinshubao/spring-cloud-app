package com.jean.auto.model;

import org.hibernate.validator.constraints.Length;

public class BaseRequest {

    @Length(max = 500)
    protected String description;

    @Length(max = 100)
    protected String name;


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
