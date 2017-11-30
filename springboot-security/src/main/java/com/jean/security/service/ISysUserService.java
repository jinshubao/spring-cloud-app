package com.jean.security.service;

import com.jean.security.domain.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author jinshubao
 */
public interface ISysUserService extends IBaseService<SysUser>, UserDetailsService {

}
