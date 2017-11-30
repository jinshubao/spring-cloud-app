package com.jean.security.repository;

import com.jean.security.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author jinshubao
 */
public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    /**
     * 按用户名查询用户
     *
     * @param username 按用户
     * @return 用户
     */
    SysUser findByUsername(String username);
}
