package com.jean.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * @author jinshubao
 */
@Entity
@DynamicUpdate
@Table(name = "t_order")
@Inheritance(strategy = InheritanceType.JOINED)
public class Order extends BaseEntity {

    private static final long serialVersionUID = -4959344565569832180L;

    private String name;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
