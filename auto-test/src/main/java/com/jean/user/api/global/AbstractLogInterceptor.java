package com.jean.user.api.global;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public abstract class AbstractLogInterceptor {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract void pointCut();

    //声明环绕通知
    @Around("pointCut()")
    protected Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return doLog(joinPoint);
    }


    protected Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Logger logger = getLogger();
        long begin = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object result = joinPoint.proceed();
        long end = System.currentTimeMillis();
        logger.info("request {} cost {} ms", method.getName(), end - begin);
        return result;
    }

    protected Logger getLogger() {
        return this.logger;
    }
}
