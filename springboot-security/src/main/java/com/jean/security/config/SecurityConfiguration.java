package com.jean.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;


@EnableWebSecurity
@Configuration
@Order(1)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableRedisHttpSession
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailService;

    @Autowired
    public SecurityConfiguration(UserDetailsService userDetailService) {
        this.userDetailService = userDetailService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpSessionStrategy sessionStrategy() {
        return new HeaderHttpSessionStrategy();
    }

//    @Bean
    FilterInvocationSecurityMetadataSource metadataSource() {
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<>();

        // 需要验证权限的地址.
        AntPathRequestMatcher matcher = new AntPathRequestMatcher("/user.jsp");
        // 权限集合.
        List<ConfigAttribute> attributes = new ArrayList<>();
        attributes.add(new SecurityConfig("ROLE_USER"));
        // 放入到requestMap中.
        requestMap.put(matcher, attributes);
        return new DefaultFilterInvocationSecurityMetadataSource(requestMap);
    }

//    @Bean
    public Filter filter(FilterInvocationSecurityMetadataSource metadataSource) {
        FilterSecurityInterceptor interceptor = new FilterSecurityInterceptor();
        interceptor.setSecurityMetadataSource(metadataSource);
        return interceptor;
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().and().logout()
                .and().authorizeRequests()
                .antMatchers("/swagger-ui.html",
                        "/validatorUrl/**",
                        "/webjars/**",
                        "/swagger-resources/**",
                        "/v2/api-docs",
                        "/configuration/**",
                        "/user/**",
//                        "/resource/**",
//                        "/authority/**",
                        "/role/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated();
    }
}
