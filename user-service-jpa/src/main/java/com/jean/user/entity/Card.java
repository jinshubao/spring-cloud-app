package com.jean.user.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Data
@Entity
@DynamicUpdate
@Table(name = "t_card")
@Inheritance(strategy = InheritanceType.JOINED)
public class Card  extends BaseEntity{
    private Integer type;
    private String cardNum;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
