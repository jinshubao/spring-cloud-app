package com.jean.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "t_user")
@DynamicUpdate
@Inheritance(strategy = InheritanceType.JOINED)
public class User extends BaseEntity {

    @Column(unique = true, nullable = false, updatable = false)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Order> orders;

    @OneToOne(mappedBy = "user")
    private Card card;


}
