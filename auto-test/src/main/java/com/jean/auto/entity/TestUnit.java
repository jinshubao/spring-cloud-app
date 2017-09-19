package com.jean.auto.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DynamicUpdate
@Table(name = "t_test_unit")
public class TestUnit extends BaseEntity {

}
