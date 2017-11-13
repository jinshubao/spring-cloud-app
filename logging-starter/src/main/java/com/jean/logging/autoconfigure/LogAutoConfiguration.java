package com.jean.logging.autoconfigure;

import com.jean.logging.aop.LogMethodInterceptor;
import org.aopalliance.aop.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.PostConstruct;

/**
 * @author jinshubao
 * @date 2017/7/11
 */
@ConditionalOnProperty(havingValue = "logging.url")
@EnableConfigurationProperties(LogProperties.class)
public class LogAutoConfiguration extends AbstractPointcutAdvisor {
    private Logger logger = LoggerFactory.getLogger(LogAutoConfiguration.class);

    private Pointcut pointcut;

    private Advice advice;

    private final LogProperties logProperties;

    @Autowired
    public LogAutoConfiguration(LogProperties logProperties) {
        this.logProperties = logProperties;
    }

    @PostConstruct
    public void init() {
        logger.info("init logging configuration...");
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(logProperties.getExpression());
        pointcut.setLocation(logProperties.getLocation());
        this.pointcut = pointcut;
        this.advice = new LogMethodInterceptor(logProperties.getLogParams(), logProperties.getLogResult(), logProperties.getLogCost());
        logger.info("configuration initialized");
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

}
