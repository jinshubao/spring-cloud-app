package com.jean.user.service;

import com.jean.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
public interface IUserService {

    Page<User> list(Pageable pageable);

    List<User> findAll();

    User findOne(Long id);
}
