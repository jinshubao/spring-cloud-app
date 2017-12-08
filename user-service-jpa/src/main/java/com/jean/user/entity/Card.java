package com.jean.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author jinshubao
 */
@Entity
@DynamicUpdate
@Table(name = "t_card")
@Inheritance(strategy = InheritanceType.JOINED)
public class Card extends BaseEntity {

    private static final long serialVersionUID = 1088525372889339504L;

    private Integer type;

    private String cardNum;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
