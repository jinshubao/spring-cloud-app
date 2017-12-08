package com.jean.user.repository;

import com.jean.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param name
     * @return
     */
    List<User> findByUsername(@Param("name") String name);
}
