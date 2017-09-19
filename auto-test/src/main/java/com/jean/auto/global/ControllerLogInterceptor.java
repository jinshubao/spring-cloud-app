package com.jean.auto.global;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(0)
public class ControllerLogInterceptor extends AbstractLogInterceptor {

    @SuppressWarnings("unused")
    @Pointcut("execution(* com.jean.auto.controller..*.*(..))")
    protected void pointCut() {
    }

}
