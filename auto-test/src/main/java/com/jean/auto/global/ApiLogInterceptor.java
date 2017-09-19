package com.jean.auto.global;

import com.jean.auto.controller.BaseController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;

@Component
@Aspect
@Order(0)
public class ApiLogInterceptor extends AbstractLogInterceptor{

    @Override
    @Pointcut("execution(* com.jean.auto.api..*.*(..))")
    protected void pointCut() {
    }

}
