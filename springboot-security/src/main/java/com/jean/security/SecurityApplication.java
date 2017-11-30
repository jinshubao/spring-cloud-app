package com.jean.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

import java.util.List;
import java.util.Map;

/**
 * @author jinshubao
 */
@SpringBootApplication
public class SecurityApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecurityApplication.class, args);
        Map<String, AbstractSecurityInterceptor> beans = context.getBeansOfType(AbstractSecurityInterceptor.class);
        Map<String, AbstractValueAdaptingCache> caches = context.getBeansOfType(AbstractValueAdaptingCache.class);
        Map<String, AccessDecisionVoter> accessDecisionVoters = context.getBeansOfType(	AccessDecisionVoter.class);
        System.out.println(beans.size());
        System.out.println(caches.size());
        System.out.println(accessDecisionVoters.size());
    }
}
