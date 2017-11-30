package com.jean.security.config.sec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.stereotype.Component;

/**
 * TODO
 *
 * @author jinshubao
 * @since TODO
 */
@Component
public class MyObjectPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {

    private final AccessDecisionManager accessDecisionManager;

    private final FilterInvocationSecurityMetadataSource securityMetadataSource;

    @Autowired
    public MyObjectPostProcessor(AccessDecisionManager accessDecisionManager, FilterInvocationSecurityMetadataSource securityMetadataSource) {
        this.accessDecisionManager = accessDecisionManager;
        this.securityMetadataSource = securityMetadataSource;
    }

    @Override
    public <O extends FilterSecurityInterceptor> O postProcess(O interceptor) {

        interceptor.setAccessDecisionManager(accessDecisionManager);
        interceptor.setSecurityMetadataSource(securityMetadataSource);
        return interceptor;
    }
}
