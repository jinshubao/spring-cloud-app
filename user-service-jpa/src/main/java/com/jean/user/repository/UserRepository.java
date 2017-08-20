package com.jean.user.repository;

import com.jean.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(path = "people")
public interface UserRepository extends JpaRepository<User, Long> {
    @RestResource(path = "names")
    List<User> findByUsername(@Param("name") String name);
}
