package com.jean.logging.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by jinshubao on 2017/7/11.
 */
public class LogMethodInterceptor implements MethodInterceptor {
    private Logger logger = LoggerFactory.getLogger(LogMethodInterceptor.class);

    private Boolean logParams;

    private Boolean logResult;

    private Boolean logCost;

    public LogMethodInterceptor() {
    }

    public LogMethodInterceptor(Boolean logParams, Boolean logResult, Boolean logCost) {
        this.logParams = logParams;
        this.logResult = logResult;
        this.logCost = logCost;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        Object[] arguments = invocation.getArguments();
        long start = System.currentTimeMillis();
        Object result = invocation.proceed();
        long end = System.currentTimeMillis();
        if (logParams) {
            logger.info("====method({}), params({}) ", methodName, arguments);
        }
        if (logResult) {
            logger.info("====method({}), result({}) ", methodName, result);
        }
        if (logCost) {
            logger.info("====method({}), cost({}) ", methodName, (end - start));
        }
        return result;
    }

    public Boolean getLogParams() {
        return logParams;
    }

    public void setLogParams(Boolean logParams) {
        this.logParams = logParams;
    }

    public Boolean getLogResult() {
        return logResult;
    }

    public void setLogResult(Boolean logResult) {
        this.logResult = logResult;
    }

    public Boolean getLogCost() {
        return logCost;
    }

    public void setLogCost(Boolean logCost) {
        this.logCost = logCost;
    }
}
