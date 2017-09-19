package com.jean.auto.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@DynamicUpdate
@Table(name = "t_project", uniqueConstraints = @UniqueConstraint(name = "t_project_unique_name", columnNames = "name"))
public class Project extends BaseEntity {

}
