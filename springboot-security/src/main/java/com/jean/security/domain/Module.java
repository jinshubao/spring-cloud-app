package com.jean.security.domain;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * 模块
 *
 * @author jinshubao
 * @since 1.0
 */
@Entity
@DynamicUpdate
@Table(name = "sys_module")
@Inheritance(strategy = InheritanceType.JOINED)
public class Module extends BaseEntity {
}
