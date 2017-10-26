package com.jean.security.repository;

import com.jean.security.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author jinshubao
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 按用户名查询用户
     * @param username 按用户
     * @return 用户
     */
    SysUser findByUsername(String username);
}
