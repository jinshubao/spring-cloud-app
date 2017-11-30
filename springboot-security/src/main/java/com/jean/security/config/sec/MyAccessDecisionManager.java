package com.jean.security.config.sec;


import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityMessageSource;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Objects;

/**
 * 权限决策
 *
 * @author jinshubao
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    private MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    /**
     * 决策当前的访问是否能通过权限验证
     *
     * @param authentication   包含了当前的用户信息，包括拥有的权限
     * @param object           FilterInvocation对象，可以得到request等web资源
     * @param configAttributes 是本次访问需要的权限
     * @throws AccessDeniedException               没有权限
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (ConfigAttribute configAttribute : configAttributes) {
            for (GrantedAuthority authority : authorities) {
                //拥有任何一个匹配的权限就可以访问
                if (Objects.equals(authority.getAuthority(), configAttribute.getAttribute())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException(messages.getMessage(
                "AbstractAccessDecisionManager.accessDenied", "Access is denied"));
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
