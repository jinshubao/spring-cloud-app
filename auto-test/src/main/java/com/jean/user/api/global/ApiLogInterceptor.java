package com.jean.user.api.global;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(0)
public class ApiLogInterceptor extends AbstractLogInterceptor{

    @Override
    @Pointcut("execution(* com.jean.user.api.api..*.*(..))")
    protected void pointCut() {
    }

}
