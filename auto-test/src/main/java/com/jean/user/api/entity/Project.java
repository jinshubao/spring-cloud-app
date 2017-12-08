package com.jean.user.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@DynamicUpdate
@Table(name = "t_project", uniqueConstraints = @UniqueConstraint(name = "t_project_unique_name", columnNames = "name"))
public class Project extends BaseEntity {

    @JsonIgnore
    @OneToMany(mappedBy = "project")
    private List<Module> modules;

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
