package com.jean.user.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;


@Data
@Entity
@DynamicUpdate
@Table(name = "t_order")
@Inheritance(strategy = InheritanceType.JOINED)
public class Order extends BaseEntity {

    private String name;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
