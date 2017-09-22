package com.jean.auto.model.response;

import java.util.List;

public class ProjectResponse {

    private Long id;

    private String name;

    private String description;

    private List<ModuleResponse> modules;

    public ProjectResponse(Long id, String name, String description, List<ModuleResponse> modules) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.modules = modules;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ModuleResponse> getModules() {
        return modules;
    }

    public void setModules(List<ModuleResponse> modules) {
        this.modules = modules;
    }
}
